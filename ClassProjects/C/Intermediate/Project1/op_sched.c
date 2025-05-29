/* This is the only file you will be editing.
 * - Copyright of Starter Code: Prof. Kevin Andrea, George Mason University.  All Rights Reserved
 * - Copyright of Student Code: You!  
 * - Restrictions on Student Code: Do not post your code on any public site (eg. Github).
 * -- Feel free to post your code on a PRIVATE Github and give interviewers access to it.
 * -- You are liable for the protection of your code from others.
 * - Date: Jan 2023
 */

/* Fill in your Name and GNumber in the following two comment fields
 * Name: Arron Birhanu
 * GNumber: G01315277
 */

// System Includes
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <pthread.h>
#include <sched.h>
// Local Includes
#include "op_sched.h"
#include "vm_support.h"
#include "vm_process.h"

// Feel free to use these in your code, if you like! 
#define CRITICAL_FLAG   (1 << 31) 
#define LOW_FLAG        (1 << 30) 
#define READY_FLAG      (1 << 29)
#define DEFUNCT_FLAG    (1 << 28)
int deleteProcess(Op_queue_s *queue, pid_t pid);
void addLast(Op_queue_s *queue, Op_process_s *process);
/* Feel free to create any helper functions you like! */

/* Initializes the Op_schedule_s Struct and all of the Op_queue_s Structs
 * Follow the project documentation for this function.
 * Returns a pointer to the new Op_schedule_s or NULL on any error.
 */
Op_schedule_s *op_create() {
  Op_schedule_s *schedule = malloc(sizeof (Op_schedule_s));
  schedule->ready_queue_high = malloc(sizeof (Op_queue_s));
  schedule->ready_queue_low = malloc(sizeof (Op_queue_s));
  schedule->defunct_queue = malloc(sizeof (Op_queue_s));
  schedule->ready_queue_high->count = 0;
  schedule->ready_queue_low->count = 0;
  schedule->defunct_queue->count = 0;
  schedule->ready_queue_high->head = NULL;
  schedule->ready_queue_low->head = NULL;
  schedule->defunct_queue->head = NULL;
  if(schedule==NULL)
	  return NULL;
  if(schedule->ready_queue_high==NULL)
	  return NULL;
  if(schedule->ready_queue_low==NULL)
	  return NULL;
  if(schedule->defunct_queue==NULL)
	  return NULL;
  
  return schedule; // Replace This Line with Your Code!
}

/* Create a new Op_process_s with the given information.
 * - Malloc and copy the command string, don't just assign it!
 * Follow the project documentation for this function.
 * Returns the Op_process_s on success or a NULL on any error.
 */
Op_process_s *op_new_process(char *command, pid_t pid, int is_low, int is_critical) {
  Op_process_s *process = malloc(sizeof (Op_process_s));
  process->pid = pid;
  process->cmd = malloc(sizeof (char) * (strlen(command)+1));
  process->next = NULL;
  process->age = 0;
  process->state = 0;
  process->state |= READY_FLAG;
  process->state &= ~DEFUNCT_FLAG;
  if(is_low==1)
	  process->state |= LOW_FLAG;
  if(is_critical==1)
	  process->state |= CRITICAL_FLAG;
  strcpy(process->cmd, command);
  return process; // Replace This Line with Your Code!
}

/* Adds a process into the appropriate singly linked list queue.
 * Follow the project documentation for this function.
 * Returns a 0 on success or a -1 on any error.
 */
int op_add(Op_schedule_s *schedule, Op_process_s *process) {
  if(process==NULL||schedule==NULL||schedule->ready_queue_high==NULL||schedule->ready_queue_low==NULL||schedule->defunct_queue==NULL)
	  return -1;

  process->state |= READY_FLAG;//Ready State!
  process->state &= ~DEFUNCT_FLAG;

  if(((process->state&LOW_FLAG)>>30)==0){//high priority
	if(schedule->ready_queue_high->head==NULL){//empty high priority queue?
		schedule->ready_queue_high->head = process;
		schedule->ready_queue_high->count+=1;
		return 0;
	}
	if(schedule->ready_queue_high->head!=NULL){//non-empty high priority queue?
		Op_process_s *curr = schedule->ready_queue_high->head;//traverse the linked list
    Op_process_s *prev = schedule->ready_queue_high->head;
		while(curr!=NULL){
      prev = curr;
			curr = curr->next;
    }
    prev->next = process;
		schedule->ready_queue_high->count+=1;
		return 0;
	}
  }


  if(((process->state&LOW_FLAG)>>30)==1){//low priority
  	if(schedule->ready_queue_low->head==NULL){//empty low priority queue?
		schedule->ready_queue_low->head = process;
		schedule->ready_queue_low->count+=1;
		return 0;
	}
	if(schedule->ready_queue_low->head!=NULL){//non-empty low priority queue?
		Op_process_s *curr = schedule->ready_queue_low->head;//traverse the linked list
    Op_process_s *prev = schedule->ready_queue_low->head;
		while(curr!=NULL){
      prev = curr;
			curr = curr->next;
    }

    prev->next = process;
		schedule->ready_queue_low->count+=1;
		return 0;
	}
  }
  return -1; // Replace This Line with Your Code!
}

/* Returns the number of items in a given Op_queue_s
 * Follow the project documentation for this function.
 * Returns the number of processes in the list or -1 on any errors.
 */	
int op_get_count(Op_queue_s *queue) {
  if(queue==NULL)
    return -1;
  return queue->count; // Replace This Line with Your Code!
}

/* Selects the next process to run from the High Ready Queue.
 * Follow the project documentation for this function.
 * Returns the process selected or NULL if none available or on any errors.
 */
Op_process_s *op_select_high(Op_schedule_s *schedule) {
  if(schedule==NULL||schedule->ready_queue_high==NULL||op_get_count(schedule->ready_queue_high)==0)
	  return NULL;
  Op_process_s *curr = schedule->ready_queue_high->head;
  while(curr!=NULL){
    if((curr->state&CRITICAL_FLAG)>>31==1){//if critical process is found
      deleteProcess(schedule->ready_queue_high, curr->pid);//remove from the high priority queue
      curr->next = NULL;
      curr->age = 0;
      schedule->ready_queue_high->count-=1;
      if(schedule->ready_queue_high->count==0)
        schedule->ready_queue_high->head = NULL;
      return curr;
    }
    curr = curr->next;//traverse
  }
  curr = schedule->ready_queue_high->head;//if no critical process is found, select and remove the first high priority process
  schedule->ready_queue_high->head = schedule->ready_queue_high->head->next;
  curr->next = NULL;
  curr->age = 0;
  schedule->ready_queue_high->count-=1;
  return curr; // Replace This Line with Your Code!
}

/* Schedule the next process to run from the Low Ready Queue.
 * Follow the project documentation for this function.
 * Returns the process selected or NULL if none available or on any errors.
 */
Op_process_s *op_select_low(Op_schedule_s *schedule) {
  if(schedule==NULL||schedule->ready_queue_low==NULL||op_get_count(schedule->ready_queue_low)==0)//null check
	  return NULL;
  Op_process_s *remove = schedule->ready_queue_low->head;//node to be removed from low priority queue
  
  schedule->ready_queue_low->head = schedule->ready_queue_low->head->next;//get rid of the front of the queue (head)
  remove->next = NULL;//node to be removed is no longer the head and now points to nothing
  remove->age = 0;//the age is reset
  schedule->ready_queue_low->count-=1;//counter
  return remove; // Replace This Line with Your Code!
}

/* Add age to all processes in the Ready - Low Priority Queue, then
 *  promote all processes that are >= MAX_AGE.
 * Follow the project documentation for this function.
 * Returns a 0 on success or -1 on any errors.
 */

int op_promote_processes(Op_schedule_s *schedule) {
  if(schedule==NULL||schedule->ready_queue_low==NULL||schedule->ready_queue_high==NULL)
	 return -1;
   if(schedule->ready_queue_low->count==0)//empty low priority queue
     return 0;
  Op_process_s *curr = schedule->ready_queue_low->head;//low priority queue
  while(curr!=NULL){
    curr->age++;
    curr = curr->next;
  }//increment age of all low queue processes by one

  curr = schedule->ready_queue_low->head;
  while(curr!=NULL){//iterate through low queue
    if(curr->age>=MAX_AGE){//look for any ages that are greater than max age
      deleteProcess(schedule->ready_queue_low, curr->pid);//delete from low priority queue
      curr->age=0;
      curr->next = NULL;
      addLast(schedule->ready_queue_high, curr);//add to high priority queue
      schedule->ready_queue_high->count+=1;//increment
      schedule->ready_queue_low->count-=1;
      if(schedule->ready_queue_low->count==0)
        schedule->ready_queue_low->head = NULL;
      curr = schedule->ready_queue_high->head;//back to start|
      continue;//start from scratch, skip traversing      <--+
    }
    curr = curr->next;//traverse
  }

  return 0; // Replace This Line with Your Code!
}
void addLast(Op_queue_s *queue, Op_process_s *process)
{

    //if head is NULL, it is an empty list
    if(queue->head == NULL)
         queue->head = process;
    //Otherwise, find the last node and add the newNode
    else
    {
        struct process_node *lastNode = queue->head;
        struct process_node *prev = queue->head;
        //last node's next address will be NULL.
        while(lastNode != NULL)
        {
            prev = lastNode;
            lastNode = lastNode->next;
        }

        //add the newNode at the end of the linked list
        prev->next = process;
    }

}
//helper remove node method
int deleteProcess(Op_queue_s *queue, pid_t pid)
{
    // Store head node
    struct process_node *temp = queue->head;
    // If head node itself holds the key to be deleted
    if (temp != NULL && temp->pid == pid) {
        queue->head = temp->next; // Changed head
        return 1;
    }
 
    // Search for the key to be deleted, keep track of the
    // previous node as we need to change 'prev->next'
    struct process_node *prev = queue->head;
    while (temp != NULL && temp->pid != pid) {
        prev = temp;
        temp = temp->next;
    }
 
    // If key was not present in linked list
    if (temp == NULL)
        return 0;
 
    // Unlink the node from linked list
    prev->next = temp->next;

    return 1;
}
/* This is called when a process exits normally.
 * Put the given node into the Defunct Queue and set the Exit Code 
 * Follow the project documentation for this function.
 * Returns a 0 on success or a -1 on any error.
 */
int op_exited(Op_schedule_s *schedule, Op_process_s *process, int exit_code) {
  if(schedule==NULL||process==NULL)
    return -1;
  
  process->state &= ~READY_FLAG;
  process->state |= DEFUNCT_FLAG;
  process->state &= 0xF0000000;//clear 28 bits to take in the exit code
  process->state |= exit_code;//replace the cleared 28 bits with the exit code
  Op_process_s *curr = schedule->defunct_queue->head;
  Op_process_s *prev = schedule->defunct_queue->head;
  if(curr==NULL){
    curr = process;
    schedule->defunct_queue->count+=1;
    return 0;
  }//defunct queue empty

  while(curr!=NULL){
    prev = curr;
    curr = curr->next;
  }//traverse if not empty
  prev->next = process;//add to defunct queue
  schedule->defunct_queue->count+=1;
  return 0; // Replace This Line with Your Code!
}


/* This is called when the OS terminates a process early.
 * Remove the process with matching pid from Ready High or Ready Low and add the Exit Code to it.
 * Follow the project documentation for this function.
 * Returns a 0 on success or a -1 on any error.
 */
int op_terminated(Op_schedule_s *schedule, pid_t pid, int exit_code) {
  if(schedule==NULL)
    return -1;
  
  Op_process_s *curr = schedule->defunct_queue->head;

  while(curr!=NULL){
    if(curr->pid==pid){
      break;
    }
    curr = curr->next;
  }//iterate high priority queue

  if(deleteProcess(schedule->ready_queue_high, pid)==1){//found in high prioirity queue
    schedule->ready_queue_high->count-=1;
    curr->next = NULL;
    curr->state &= ~READY_FLAG;
    curr->state |= DEFUNCT_FLAG;
    curr->state &= 0xF0000000;//clear 28 bits to take in the exit code
    curr->state |= exit_code;//replace the cleared 28 bits with the exit code
    Op_process_s *start = schedule->defunct_queue->head;
    Op_process_s *temp = schedule->defunct_queue->head;
    if(start==NULL){
      start = curr;
      schedule->defunct_queue->count+=1;
      return 0;
    }//defunct queue empty

    while(start!=NULL){
      temp = start;
      start = start->next;
    }//traverse if not empty
    temp->next = curr;//add to defunct queue
    schedule->defunct_queue->count+=1;
    return 0;
  }

  curr = schedule->ready_queue_low->head;
  while(curr!=NULL){
    if(curr->pid==pid){
      break;
    }
    curr = curr->next;
  }//iterate low priority queue

  if(deleteProcess(schedule->ready_queue_low, pid)==1){
    schedule->ready_queue_low->count-=1;
    curr->next = NULL;
    curr->state &= ~READY_FLAG;
    curr->state |= DEFUNCT_FLAG;
    curr->state &= 0xF0000000;//clear 28 bits to take in the exit code
    curr->state |= exit_code;//replace the cleared 28 bits with the exit code
    Op_process_s *start = schedule->defunct_queue->head;
    Op_process_s *temp = schedule->defunct_queue->head;
    if(start==NULL){
      start = curr;
      schedule->defunct_queue->count+=1;
      return 0;
    }//defunct queue empty

    while(start!=NULL){
      temp = start;
      start = start->next;
    }//traverse if not empty
    temp->next = curr;//add to defunct queue
    schedule->defunct_queue->count+=1;
    return 0;
  }
  
  return -1;

}

/* Frees all allocated memory in the Op_schedule_s, all of the Queues, and all of their Nodes.
 * Follow the project documentation for this function.
 */
void op_deallocate(Op_schedule_s *schedule) {
  Op_process_s *curr = schedule->defunct_queue->head->next;
  free(schedule->defunct_queue->head);
  while(schedule->defunct_queue->head!=NULL){//free defunct priority queue command strings
    
    free(schedule->defunct_queue->head->cmd);
    curr = schedule->defunct_queue->head->next;
    schedule->defunct_queue->head = curr;
    
  }

  curr = schedule->ready_queue_high->head->next;
  free(schedule->ready_queue_high->head);
  while(schedule->ready_queue_high->head!=NULL){//free high priority queue command strings

    free(schedule->ready_queue_high->head->cmd);
    curr = schedule->ready_queue_high->head->next;
    schedule->ready_queue_high->head = curr;
  }
  
  curr = schedule->ready_queue_low->head;
  free(schedule->ready_queue_low->head);
  while(schedule->ready_queue_low->head!=NULL){//free low priority queue command strings
    
    free(schedule->ready_queue_low->head->cmd);
    curr = schedule->ready_queue_low->head->next;
    schedule->ready_queue_low->head = curr;
  }

  curr = schedule->defunct_queue->head;
  free(schedule->defunct_queue->head);
  while(schedule->defunct_queue->head!=NULL){//free defunct priority queue

    free(schedule->defunct_queue->head);
    curr = schedule->defunct_queue->head->next;
    schedule->defunct_queue->head = curr;
  }

  curr = schedule->ready_queue_high->head;
  free(schedule->ready_queue_high->head);
  while(schedule->ready_queue_high->head!=NULL){//free high priority queue

    free(schedule->ready_queue_high->head);
    curr = schedule->ready_queue_high->head->next;
    schedule->ready_queue_high->head = curr;
  }

  curr = schedule->ready_queue_low->head;
  free(schedule->ready_queue_low->head);
  while(schedule->ready_queue_low->head!=NULL){//free high priority queue

    free(schedule->ready_queue_low->head);
    curr = schedule->ready_queue_low->head->next;
    schedule->ready_queue_low->head = curr;
  }

  free(schedule->defunct_queue);
  free(schedule->ready_queue_high);
  free(schedule->ready_queue_low);
  free(schedule);
  return; // Replace This Line with Your Code!
}
