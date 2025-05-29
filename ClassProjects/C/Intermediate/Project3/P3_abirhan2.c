/* This is the only file you should update and submit. */

/* Fill in your Name and GNumber in the following two comment fields
 * Name: Arron Birhanu
 * GNumber: G01315277
 */

#include <sys/wait.h>
#include "logging.h"
#include "anav.h"
#include "parse.h"
#include "util.h"

/* Constants */
#define DEBUG 0 /* You can set this to 0 to turn off the debug parse information */
#define STOP_SHELL  0
#define RUN_SHELL   1


typedef struct taskInfo{ //details of a given task
  int TID; //task id
  pid_t PID; //process id
  int status; //task status
  int exitCode; //if terminated
  char *cmd; //task command
}taskInfo;

typedef struct node{
    struct node* next;
    taskInfo* data;
}Node;

Node *addTask(Node *head, char *cmd);
Node *removeTask(Node *head, int taskID);
int busyTask(Node *head, int taskID);
int containsTask(Node *head, int taskID);

int containsTask(Node *head, int taskID){
  Node *curr = head;
  while(curr!=NULL){
    if(curr->data->TID==taskID){
      return 1;
    }
    curr = curr->next;
  }
  return 0;
}//checks if a task exists in the task list

int busyTask(Node *head, int taskID){
  Node *curr = head;
  while(curr!=NULL){
    if(curr->data->TID==taskID){
      if(curr->data->status==LOG_STATE_RUNNING || curr->data->status==LOG_STATE_SUSPENDED)
        return 1;
    }
    curr = curr->next;
  }
  return 0;
}//checks if a task is busy

Node *addTask(Node *head, char *cmd){
  Node *newNode = malloc(sizeof(Node));
  taskInfo *newData = malloc(sizeof(taskInfo));

  newData->exitCode = 0;
  newData->PID = 0;
  newData->status = LOG_STATE_READY;
  newData->cmd = string_copy(cmd);

  newNode->next = NULL;
  newNode->data = newData;
  
  if(head==NULL){
    newNode->data->TID = 1;
    head = newNode;
    return head;
  }//if task list is empty

  //if task list has at least one task already
  Node *curr = head;
  
  while(curr->next!=NULL){
    curr = curr->next;
  }//iterate the task list

  newNode->data->TID = 1+(curr->data->TID);
  curr->next = newNode;

  return head;

}//adding a task to the linked list of tasks

Node *removeTask(Node *head, int taskID){
  if(head->data->TID==taskID){
    Node *freed = head;
    head = head->next;
    free(freed->data->cmd);
    free(freed->data);
    free(freed);
    return head;
  }//first task will be removed and freed

  Node *curr = head;//traverse the linked list
  while(curr->next!=NULL){
    if(curr->next->data->TID==taskID){
      Node *freed = curr->next;
      curr->next = curr->next->next;
      free(freed->data->cmd);
      free(freed->data);
      free(freed);
      return head;
    }//the taskid matching will be removed and freed
    curr = curr->next;
  }

  return head;//should not reach this case but for reassurance measure
}//when removing a task remember to free it and its contents

/* The entry of your text processor program */
int main() {
  char *cmd = NULL;
  int do_run_shell = RUN_SHELL;
  
  /* Intial Prompt and Welcome */
  log_anav_intro();
  log_anav_help();
  Node *head = NULL;
  /* Shell looping here to accept user command and execute */
  while (do_run_shell == RUN_SHELL) {
    char *argv[MAXARGS+1] = {0};  /* Argument list */
    Instruction inst = {0};       /* Instruction structure: check parse.h */
    
    /* Print prompt */
    log_anav_prompt();

    /* Get Input - Allocates memory for the cmd copy */
    cmd = get_input(); 
    /* If the input is whitespace/invalid, get new input from the user. */
    if(cmd == NULL) {
      continue;
    }

    /* Check to see if this is the quit built-in */
    if(strncmp(cmd, "quit", 4) == 0) {
      /* This is a match, so we'll set the main loop to exit when you finish processing it */
      do_run_shell = STOP_SHELL;
      /* Note: You will need to print a message when quit is entered, 
       *       you can do it here, in your functions, or at the end of main.
       */
      log_anav_quit();
    }

    if( (strncmp(cmd, "help", 4) != 0) && (strncmp(cmd, "quit", 4) != 0) && (strncmp(cmd, "list", 4) != 0) && (strncmp(cmd, "purge", 5) != 0) && (strncmp(cmd, "exec", 4) != 0) && (strncmp(cmd, "bg", 2) != 0) && (strncmp(cmd, "pipe", 4) != 0) && (strncmp(cmd, "kill", 4) != 0) && (strncmp(cmd, "suspend", 7) != 0) && (strncmp(cmd, "resume", 6) != 0) ){
      head = addTask(head, cmd);//add task to list of tasks

      Node *curr = head;
      while(curr->next!=NULL){
        curr = curr->next;
      }
      log_anav_task_init(curr->data->TID, cmd);//log the recently added task
    }
    //if it's not a built in command it's a task

    /* Parse the Command and Populate the Instruction and Arguments */
    initialize_command(&inst, argv);    /* initialize arg lists and instruction */
    parse(cmd, &inst, argv);            /* call provided parse() */

    if (DEBUG) {  /* display parse result, redefine DEBUG to turn it off */
      debug_print_parse(cmd, &inst, argv, "main (after parse)");
    }

    /*.===============================================.
     *| Add your code below this line to continue. 
     *| - The command has been parsed and you have cmd, inst, and argv filled with data.
     *| - Very highly recommended to start calling your own functions after this point.
     *o===============================================*/
    //looking for help
    if(strncmp(cmd, "help", 4) == 0){
      log_anav_help();
    }

    //printing a list of tasks
    if(strncmp(cmd, "list", 4) == 0){
      if(head==NULL){//if there are 0 tasks to show
        log_anav_num_tasks(0);
        continue;
      }
      int numTask = 1;//log the number of tasks in the list before providing info on each task
      Node *curr = head;
      while(curr->next!=NULL){
        curr = curr->next;
        numTask++;
      }
      log_anav_num_tasks(numTask);//log number of tasks

      curr = head;
      while(curr!=NULL){
        log_anav_task_info(curr->data->TID, curr->data->status, curr->data->exitCode, curr->data->PID, curr->data->cmd);
        curr = curr->next;
      }//log info on each task
    }

    //purge a task
    if(strncmp(cmd, "purge", 5) == 0){
      int taskID = inst.id1;//the id of the task to be purged
      if(!containsTask(head, taskID)){//if the task id attempted to be purged does not exist
        log_anav_task_num_error(taskID);
      }
      else{
        if(busyTask(head, taskID)){//check if task is busy
          Node *curr = head;
          int status = 0;

          while(curr!=NULL){
            if(curr->data->TID == taskID){
              status = curr->data->status;
              break;
            }
            curr = curr->next;
          }//find the status of the task

          log_anav_status_error(taskID, status);//log the busy task down as an error while purging
          continue;
        }//task was busy

        head = removeTask(head, taskID);
        log_anav_purge(taskID);
        //remove the task
      }
    }



    if(strncmp(cmd, "exec", 4) == 0){
      printf("In file: %s\n",inst.infile);
    
      Node *curr = head;
      int exitCode = 0;
      if(containsTask(head, inst.id1)==0){
        log_anav_task_num_error(inst.id1);
        continue;
      }//if the task number doesn't exist leave conditional and log task num error

      while(curr!=NULL){
        if(curr->data->TID==inst.id1){
          break;
        }
        curr = curr->next;
      }//locate the task in the list - curr

      parse(curr->data->cmd, &inst, argv);

      if(curr->data->status==LOG_STATE_SUSPENDED || curr->data->status==LOG_STATE_RUNNING){
        log_anav_status_error(inst.id1, curr->data->status);
        continue;
      }//if task is busy leave conditional and log task status error


      



      if(inst.infile == 0){//if it's a single command (no redirection)
      pid_t PID = fork();

      if(PID==0){
        setpgid(0,0);
        log_anav_status_change(inst.id1, getpid(), LOG_FG, curr->data->cmd, LOG_START);//after forking the process
        char path1[100] = "./";
        char path2[100] = "/usr/bin/";
        

        strncat(path1, argv[0], 50);


        if(execv(path1, argv)==-1){

          strncat(path2, argv[0], 50);

          if(execv(path2, argv)==-1){    
            log_anav_exec_error(cmd);   
            exit(1);
          }
          exit(0);
        }
        exit(0);
      }//child process is created

      else{
      
      int status;
      waitpid(PID, &status, 0);
      exitCode = WEXITSTATUS(status);
      printf("exitCode = %d\n", exitCode);
      //parent process

      log_anav_status_change(inst.id1, PID, LOG_FG, curr->data->cmd, LOG_TERM);//after process finishes
      curr->data->status = LOG_STATE_FINISHED;
      curr->data->exitCode = exitCode;
      curr->data->PID = PID;
      
      }//parent process

    }


    }//executing a task in the foreground


    


    /*.===============================================.
     *| After your code: We cleanup before Looping to the next command.
     *| free_command WILL free the cmd, inst and argv data.
     *| Make sure you've copied any needed information to your Task first.
     *| Hint: You can use the util.c functions for copying this information.
     *o===============================================*/
    free_command(cmd, &inst, argv);
    cmd = NULL;
  }//end of shell loop


  //free the tasks//

  Node *curr = head;
  while(curr!=NULL){
        Node *freed = curr;
        curr = curr->next;
        free(freed->data->cmd);
  }//free cmd in each task

  curr = head;
  while(curr!=NULL){
        Node *freed = curr;
        curr = curr->next;
        free(freed->data);
  }//free task data/info in each task

  curr = head;
  while(curr!=NULL){
        Node *freed = curr;
        curr = curr->next;
        free(freed);
  }//free tasks


  return 0;
}
