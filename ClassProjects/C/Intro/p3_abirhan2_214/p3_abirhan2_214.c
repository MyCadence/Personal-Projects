/*	Arron Birhanu G01315277
 *	CS 262, Lab Section 214
 *	Project 3
 */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

typedef struct node{
	char data[50];
	struct node *next;
}node;
void addNode(node **head, char string[50]);
int strcmpa(char *s1, char *s2);
void print(node **head);
void removeNode(node **head, char string[50]);
void printSets(node **sets, int numSets);
void clear(node **head);
void copy(node **from, node **to);
void unionSets(node *list1, node *list2, node **dest);
void intersection(node *list1, node *list2, node **dest);
int containsNode(node *head, char string[50]);
void symmetricdifference(node *list1, node *list2, node **dest);

void symmetricdifference(node *list1, node *list2, node **dest){
	node *t = NULL;
	node *l1 = list1;
	node *l2 = list2;
	while(l1 != NULL){
		if(containsNode(list2, l1->data)==0)
			addNode(&t, l1->data);
		l1 = l1->next;
	}
	while(l2 != NULL){
		if(containsNode(list1, l2->data)==0)
			addNode(&t, l2->data);
		l2 = l2->next;
	}

	*dest = t;
}

int containsNode(node *head, char string[50]){
	node *t = head;
	while(t!=NULL){
		if(strcmpa(t->data, string)==0)
			return 1;
		t = t->next;
	}
	return 0;
}

void intersection(node *list1, node *list2, node **dest){
	node *t = NULL;
	node *l1 = list1;

	while(l1 != NULL){
		if(containsNode(list2, l1->data)==1)
			addNode(&t, l1->data);
		l1 = l1->next;
	}

	*dest = t;
}

void unionSets(node *list1, node *list2, node **dest){
	node *l1 = list1;
	node *l2 = list2;
	node *t = NULL;
	while(l1!=NULL){
		addNode(&t, l1->data);
		l1 = l1->next;
	}
	while(l2!=NULL){
		addNode(&t, l2->data);
		l2 = l2->next;
	}
	*dest = t;
}



void copy(node **from, node **to){
	node *l = *from;
	if(l==NULL){
		*to = NULL;
		return;
	}/*if copying an empty list into another list*/
	else{
		node *j;
		*to = malloc(sizeof(node));
		j = *to;
		strcpy(j->data, l->data);
		l=l->next;
		while(l!=NULL){
			j->next = malloc(sizeof(node));
			j=j->next;
			strcpy(j->data, l->data);
			l = l->next;
		}
		j->next = NULL;
	}
}

void clear(node **head){
	*head = NULL;


}

void printSets(node **sets, int numSets){
	int i;
	node *tempSet;
	for(i=0; i<numSets; i++){
		tempSet = sets[i];
		if(tempSet==NULL){
			continue;
		}
		printf("Set %d: ",i);
		print(&tempSet);
	}

}
void print(node **head){
	node *l = *head;
	printf("{");
	while(l!=NULL){
		if(l->next==NULL){
			printf("%s}\n",l->data);
			return;
		}
		printf("%s, ",l->data);
		l = l->next;
	}
	printf("}\n");
}

int strcmpa(char *s1, char *s2){
	while(*s1 && tolower(*s1) == tolower(*s2)){
		s1++;
		s2++;
	}
	return tolower(*s1) - tolower(*s2);
}

void removeNode(node **head, char string[50]){
	node *l;
	l = *head;
	if(strcmpa(l->data, string)==0&&l->next==NULL){
		l = NULL;
		*head = NULL;
		return;
	}/*if the data to be removed is in the head and is the only linked node*/
	if(strcmpa(l->data, string)==0&&l->next!=NULL){
		*head = l->next;
		return;
	}/*if the data to be removed is in the head and has other nodes in it*/
	while(l->next!=NULL){
		if(strcmpa(l->next->data, string)==0){
			l->next = l->next->next;
			return;
		}
		l = l->next;
	}
	if(strcmpa(l->data,string)==0)
		l = NULL;
}

void addNode(node **head, char string[50]){
	node *newNode = malloc(sizeof(node));
	node *l = *head;
	strcpy(newNode->data, string);
	newNode->next = NULL;
	if(l==NULL){
		*head = newNode;
		return;
	}/*checks if list is empty*/
	else{
		if(strcmpa(l->data, newNode->data)>=0){
			if(strcmpa(l->data, newNode->data)==0)
				return;
			newNode->next = *head;
			*head = newNode;
			return;
		}/*checks if the data is precedes the head in alphabetical value*/
		while(l->next!=NULL){
			node *tempNode;
			if(strcmpa(l->next->data, newNode->data)>=0){
				if(strcmpa(l->next->data, newNode->data)==0)
					return;
				tempNode = l->next;
				l->next = newNode;
				l->next->next = tempNode;
				return;
			}/*checks for any alphabetical precedence in the list*/
			l = l->next;/*iterate*/
		}
		l->next = newNode;/*simply add to end if reached here*/
	}/*checks if the list is not empty*/
}

int main(){
	int numSets=0;
	char inpBuf[50];
	int i;
	node **sets;
	printf("Enter the number of sets to create: ");
	fgets(inpBuf, 50, stdin);
	sscanf(inpBuf, "%d", &numSets);
	sets = malloc(numSets*sizeof(node));
	for(i = 0; i<numSets; i++){
		sets[i] = NULL;
	}
	while(0<1){
		char choice;
		int setIndex=0;
		int setIndex1=0;
		int destIndex=0;
		char inpBuf[50];
		char string[50];
		printf("\nChoose from the menu prompt below:\n");
		printf("\t***** Menu Options *****\n"
		"\tAdd String:           a or A\n"
		"\tRemove String:        r or R\n"
		"\tUnion:                u or U\n"
		"\tIntersection:         i or I\n"
		"\tSymmetric Difference: s or S\n"
		"\tCopy:                 c or C\n"
		"\tClear:                z or Z\n"
		"\tPrint Set:            P\n"
		"\tPrint All Sets:       p\n"
		"\tQuit:                 q or Q\n"
		"Select an option: ");
		fgets(inpBuf, 50, stdin);
		sscanf(inpBuf, "%c", &choice);
		printf("The choice was: %c\n", choice);
		if(choice=='a'||choice=='A'){
			printf("Enter set to add to: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &setIndex);
			printf("\nEnter string to add: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%s", string);
			addNode(&sets[setIndex], string);
			printf("\n");
			continue;
		}
		if(choice=='r'||choice=='R'){
			printf("Enter set to remove from: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &setIndex);
			printf("\nEnter the string to be removed: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%s", string);
			if(sets[setIndex]==NULL){
				printf("\nNothing to be removed!\n");
				continue;
			}
			removeNode(&sets[setIndex], string);
			printf("\n");
			continue;
		}
		if(choice=='c'||choice=='C'){
			printf("Enter set to copy to: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &setIndex);
			printf("%d\n", setIndex);
			printf("Enter set to copy from: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &setIndex1);
			printf("%d\n",setIndex1);
			clear(&sets[setIndex]);
			copy(&sets[setIndex1], &sets[setIndex]);
			continue;
		}
		if(choice=='z'||choice=='Z'){
			printf("Enter set to clear: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &setIndex);
			clear(&sets[setIndex]);
			continue;
		}
		if(choice=='p'){
			printf("Enter set to print: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &setIndex);
			printf("Set %d: ", setIndex);
			print(&sets[setIndex]);
			continue;
		}
		if(choice=='P'){
			printSets(sets, numSets);
			continue;
		}
		if(choice=='u'||choice=='U'){
			printf("Enter a destination set: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &destIndex);
			printf("Enter 1st set to union from: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &setIndex);
			printf("Enter 2nd set to union from: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &setIndex1);
			clear(&sets[destIndex]);
			unionSets(sets[setIndex], sets[setIndex1], &sets[destIndex]);
			continue;
		}
		if(choice=='i'||choice=='I'){
			printf("Enter a destination set: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &destIndex);
			printf("Enter 1st set to intersect from: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &setIndex);
			printf("Enter 2nd set to intersect from: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &setIndex1);
			clear(&sets[destIndex]);
			intersection(sets[setIndex], sets[setIndex1], &sets[destIndex]);
			continue;
		}
		if(choice=='s'||choice=='S'){
			printf("Enter a destination set: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &destIndex);
			printf("Enter 1st set to symmetrically subtract from: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &setIndex);
			printf("Enter 2nd set to symmetrically subtract with: ");
			fgets(inpBuf, 50, stdin);
			sscanf(inpBuf, "%d", &setIndex1);
			clear(&sets[destIndex]);
			symmetricdifference(sets[setIndex], sets[setIndex1], &sets[destIndex]);
			continue;
		}
		if(choice=='q'||choice=='Q')
			break;
		printf("Invalid menu option, please try again!\n");
	}
	
	for(i=0; i<numSets; i++)
		free(sets[i]);

	return 0;
}
