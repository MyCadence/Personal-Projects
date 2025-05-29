#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int is_valid_name(char *name);
int is_valid_seed();
int calculate_net_height();
int throw_ball(int net_height);
int game_logic(int calcInches, char *name);
int main(){
	char name[100];	
	int calcInches = 0;
	int playerWin = 0;
	int cpuWin = 0;
	int gameCount = 0;
	int seed = 0;
	printf("Welcome to the Gehntoo game\n");
	
	while(is_valid_name(name)==0){}

	
	seed = is_valid_seed();
	printf("Seed value to use is %d\n",seed);
	srand(seed);/*generates a new pseudocode of random numbers*/
	
	
		
	while(calcInches==0){
		calcInches=calculate_net_height();
	}/*repeat until a valid ft and in combination is produced, then calculate total inches*/
	printf("Height of the net: %d inches\n",calcInches);
	name[strcspn(name, "\n")] = 0;

	
	while(0<1){
		int result = game_logic(calcInches, name);
		char inpStr[10];
		char answer[10];
		if(result==1)
			playerWin++;
		if(result==0)
			cpuWin++;
		gameCount++;
		printf("Would you like to play again %s? Type Y or N: ",name);
		fgets(inpStr, 10, stdin);
		sscanf(inpStr, "%s", answer);
		if(strcmp(answer,"Y")==0)
			continue;
		if(strcmp(answer,"N")==0){
			printf("%s chose not to play again. Thanks for playing!\n",name);
			printf("Invalid input! Please enter 'Y' or 'N'! Try again.\n");
			break;
		}
		if(gameCount==2)
			break;
	}/*game loops until 3 games are played or user refuses to continue to play any longer*/
	printf("Printing out game statistics now:\n");
	printf("\t%s won a total of %d time(s)\n",name,playerWin);
	printf("\tComputer won a total of %d time(s)\n",cpuWin);
	return 0;/*the program is done*/
}

int game_logic(int calcInches, char *name){
	int player = 75;
	int cpu = 75;
	printf("Each player is starting with 75 points\n");
	while(player>0&&cpu>0){

	/*player turn*/
	char inpStr[10];
	int bet = 0;
	int throw = throw_ball(calcInches);
	while(bet<1||bet>11||bet>player){
		printf("%s's turn. How much would you like to bet towards your throw? ", name);
		fgets(inpStr, 10, stdin);
		sscanf(inpStr, "%d", &bet);
		if(bet<1||bet>11||bet>player)
			printf("Please enter a bet from [1-11] and/or less than the current total points available\n");
	}
	printf("\tbet %d points\n",bet);
	if(throw>calcInches){
		printf("\tBall height: %d inches, the ball is over the net\n",throw);
		player -= bet;
		printf("\tDeducting %d points\n",bet);
		printf("\t%s now has %d points\n", name, player);
	}/*throw is greater than the net*/
	if(throw<=calcInches){
		if(throw>calcInches/2){
			printf("\tBall height: %d inches, the ball is below the net\n",throw);
			player+=2;/*cannot rethrow*/
			printf("\tAdding 2 points\n");
			printf("\t%s now has %d points\n", name, player);
		}
		else{
			char inpStr[10];
			char answer[10];
			printf("\tBall height: %d inches, Throw again? Type Y or N: ",throw);
			
			fgets(inpStr, 10, stdin);
			sscanf(inpStr, "%s", answer);
			if(strcmp(answer,"Y")==0){/*if yes rethrow*/
				throw = throw_ball(calcInches);
				if(throw>calcInches){
					printf("\tBall height: %d inches, the ball is over the net\n",throw);
					player -= (bet);/*success take off bet number of points and the 2 points*/
					printf("\tDeducting %d points\n",bet);
					printf("\t%s now has %d points\n",name,player);
				}
				if(throw<=calcInches){
					player +=4;/*failure put 4 points on the player's total*/
					printf("\tBall height: %d inches, the ball is below the net\n",throw);
					printf("\tAdding 4 points\n");
					printf("\t%s now has %d points\n",name,player);
				}
			}
			if(strcmp(answer,"N")==0)/*if no rethrow*/
				player+=(bet);/*their bet is added on top of player's total*/
		}/*if throw is less than half the net height, you can ask for a rethrow...*/
		
	}/*throw is less than or equal to the net*/

	/*computer's turn*/
	printf("Computer's turn\n");
	throw = throw_ball(calcInches);
	if(throw>calcInches){/*computer throws over net*/
		cpu-=9;
		printf("\tBall height: %d inches, the ball is over the net\n",throw);
		printf("\tDeducting 9 points\n");
		printf("\tComputer now has %d points\n",cpu);
	}

	if(throw<=calcInches){
		if(throw>=calcInches/2){
			cpu+=4;
			printf("\tBall height: %d inches, the ball is below the net\n",throw);
			printf("\tAdding 4 points\n");
			printf("\tComputer now has %d points\n",cpu);
		}/*doesn't meet criteria to rethrow*/

		if(throw<calcInches/2){
			printf("\tBall height: %d inches, the ball is below the net, throwing once more\n",throw);
			throw = throw_ball(calcInches);
			if(throw<=calcInches){
				cpu+=6;
				printf("\tBall height: %d inches, the ball is below the net\n",throw);
				printf("\tAdding 6 points\n");
				printf("\tComputer now has %d points\n", cpu);
			}
			if(throw>calcInches){
				cpu-=7;
				printf("\tBall height: %d inches, the ball is over the net\n",throw);
				printf("\tDeducting 7 points\n");
				printf("\tComputer now has %d points\n",cpu);

			}
		}
	}/*computer throws under or at the net*/
	}/*iteration*/
	if(player<=0){
		printf("%s has won the current game, congratulations!\n",name);
		return 1;/*player returns 1 if won*/
	}
	if(cpu<=0){
		printf("The Computer has won the current game, better luck next time!\n");
		return 0;/*cpu returns 0 if won*/
	}
	return 1;
}

int throw_ball(int net_height){

	int num = (rand() % (net_height+17));
	return num;
}

int calculate_net_height(){
	char inpStr[100];
	char inches[10];
	char feet[10];
	int ft;
	int in;
	char outpStr[100];
	int i;
	int counter=0;
	int space=0;
	int number=0;
	printf("Please enter the height of the net in feet and inches [xxft xxin] where xx must be [1-99] ");
	fgets(inpStr, 100, stdin);
	for(i = 0; i<strlen(inpStr); i++){
		if(inpStr[i]>='0'&&inpStr[i]<='9'&&number==0)
			number = 1;
		if(number==1||number==2){
			if(space==0&&(inpStr[i]==' '||((inpStr[i]>='A'&&inpStr[i]<='Z')||(inpStr[i]>='a'&&inpStr[i]<='z'))))
				space = 1;
			if(space==1&&(inpStr[i]>='0'&&inpStr[i]<='9'))
				number = 2;
			if(space==1&&number==2&&(inpStr[i]==' '||((inpStr[i]>='A'&&inpStr[i]<='Z')||(inpStr[i]>='a'&&inpStr[i]<='z'))))
				space = 2;
			if(space==2&&number==2&&(inpStr[i]>='0'&&inpStr[i]<='9')){
				printf("Invalid input! Please try again.\n");
				return 0;
			}
		}
	}/*check if there are space characters that affect the validity of the height - condition 1*/
	for(i=0; i<strlen(inpStr);i++){
		if(inpStr[i]!=' '){
			outpStr[counter] = inpStr[i];
			counter++;
			if(inpStr[i]=='t'||inpStr[i]=='T'){
				outpStr[counter] = ' ';
				counter++;
			}
		}
	}/*format the inputted string into a computer friendly string - ex: 23ft 12in*/
	sscanf(outpStr, " %d %s %d %s", &ft, feet, &in, inches);
	if(!((ft>=1&&ft<=99)&&(in>=1&&in<=99))){
		printf("Invalid range! Make sure for both inches and feet that the net's height is between 1-99.\n");
		
		return 0;
	}/*height is not between 1-99 for either or both feet and inches*/
	if(!(strcmp(feet,"fT")==0||strcmp(feet,"Ft")==0||strcmp(feet,"ft")==0||strcmp(feet,"FT")==0)){
		printf("Invalid input! Please try again.\n");
		return 0;/*make sure the right letters are inputted for feet context*/
	}
	if(!(strcmp(inches,"iN")==0||strcmp(inches,"In")==0||strcmp(inches,"in")==0||strcmp(inches,"IN")==0)){
		printf("Invalid input! Please try again.\n");
		return 0;/*make sure the right letters are inputted for inches context*/
	}
	return (ft*12)+in;/*all conditions passed, return true (1)*/
}

int is_valid_seed(){
	int seedVal=0;
	printf("Enter a seed value to use in the range of [1-99999] ");	
	while(seedVal<1||seedVal>99999){
		int space = 0;
		int number = 0;
		char inpStr[7];
		int makeFalse = 0;
		int i;
		fgets(inpStr,7,stdin);/*before storing it at the address of our int seedVal, we must pass conditions in its String form*/
		
		for(i=0; i<strlen(inpStr);i++){
			if(!((inpStr[i]>='0'&&inpStr[i]<='9')||inpStr[i]==' '||inpStr[i]=='\n'||inpStr[i]=='\0')){
				makeFalse = 100000;
			}
		}/*check if input has any non numbers - condition 1*/

		
		for(i = 0; i<strlen(inpStr); i++){
			if(inpStr[i]>='0'&&inpStr[i]<='9')
				number = 1;
			if(number==1){
				if(space==0&&inpStr[i]==' ')
					space = 1;
				if(space==1&&(inpStr[i]>='0'&&inpStr[i]<='9'))
					makeFalse = 100000;
			}
		}/*check if there are space characters that affect the validity of the seed - condition 2*/

		sscanf(inpStr,"%d",&seedVal);
		seedVal+=makeFalse;/*make it out of the range if it did not pass all conditions of a valid seed*/
		if(seedVal<1||seedVal>99999)
			printf("Invalid seed, please enter a new seed value in the range [1-99999] ");
	}/*iteration*/
	return seedVal;/*exit with the valid seed*/
}

int is_valid_name(char *name){
	int letter=0;
	int i;	
	printf("Enter your name ");
	fgets(name,100,stdin);
	if(!(strlen(name)<=8&&strlen(name)>0)){
		printf("The name entered is invalid, please try again.\n");
		return 0;
	}/*if size of name exceeds 7 characters return false (0) - condition 1*/
	for(i=(int)strlen(name); i>=0; i--){
		if(!((name[i]>='A'&&name[i]<='Z')||(name[i]>='a'&&name[i]<='z')||name[i]==' '||name[i]=='\0'||name[i]=='\n')){
			printf("The name entered is invalid, please try again.\n");
			return 0;
		}
		if((name[i]>='A'&&name[i]<='Z')||(name[i]>='a'&&name[i]<='z')){
			letter = 1;
		}/*letter found, set letter to 1*/
	}/*if name doesn't include only space, characters, null terminator, or new line character return false - condition 2*/
	if(letter==0){
		printf("The name entered is invalid, please try again.\n");
		return 0;
	}/*if there are no letters found return false (0) - condition 3*/
	return 1;/*exit the function and return true (1)*/
}
