#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define SIZE 100

int* optimal_calc(int *arr, int**array2, int x, int y, int index, int*here);
void print_optimal(int instanceCount, int * optimal, int **optimalLoc, int r, int dx, int dy, int **map, FILE *opt);

void print_optimal(int instanceCount, int *optimal, int **optimalLoc, int r, int dx, int dy, int ** map, FILE *opt){
	int i;
	int j;
	fprintf(opt,"Instance %d:\n",instanceCount);
	for(i = 0; i < r; i++){
		fprintf(opt,"%d(%d,%d)\n",optimal[i],optimalLoc[i][0]+1,optimalLoc[i][1]+1);
	}

	for(j=dy-1; j >= 0; j--){
		for(i=0; i < dx; i++){
			if(map[i][j]==1)
				fprintf(opt,"*");
			else if(map[i][j]==2)
				fprintf(opt,"$");
			else if(map[i][j]==3)
				fprintf(opt,"@");
			else{
				map[i][j] = 0;
				fprintf(opt,"-");
			}
		}
		fprintf(opt,"\n");
	}
	fflush(opt);

}
int* optimal_calc(int *arr, int** array2, int x, int y, int index, int* here){
	int i, j;
	int symbol = 0;
	int temp=0;
	int a, b, c, d;
	for(a = 0; a<x; a++){
		for(b=0; b<y; b++){
			for(i=0; i<x; i++){
			for(j=0; j<y; j++){
				if((abs((a+1)-(i+1)))+(abs((b+1)-(j+1)))<=arr[index]&&array2[i][j]==1){
					temp++;
					if((a==i)&(b==j)){
						c = i;
						d = j;
					}
				}/*how many times you can reach a sunrise from optimal location*/
			
			}
			}
			if(temp>=here[0]){
				
				if(temp==here[0]&&b<here[2]){
					here[0] = temp;
					here[1] = a;
					here[2] = b;
					if((a==c)&&(b==d))
						symbol = 3;
					else
						symbol = 2;

				}

				if(temp==here[0]&&b==here[2]&&a<here[1]){
					here[0] = temp;
					here[1] = a;
					here[2] = b;
					if((a==c)&&(b==d))
						symbol = 3;
					else
						symbol = 2;
				}

				if(temp>here[0]){
					here[0] = temp;
					here[1] = a;
					here[2] = b;
					if((a==c)&&(b==d))
						symbol = 3;
					else
						symbol = 2;
				}

			}
			temp = 0;
		}
	}
	if(here[0]==0){
		here[0] = -1;
		here[1] = -1;
		here[2] = -1;
		return here;
	}
	
	array2[here[1]][here[2]] = symbol;

	return here;
}
int main(int argc, char **argv){
	char inBuf[SIZE];
	int dx, dy, n, r;/*dx is max x interval and dy likewise for y interval, n is number of locations, r is survey responses*/
	int a=0;
	FILE *ipt;
	FILE *opt;
	int instanceCount=1;/*how many instances*/	
	int i=0;/*iterator*/
	/*int j=0; inner iterator just in case*/
	int x,y,z; /*place holder coordinates*/
	n=-1;
	x=0;
	y=0;
	z=0;
	
	if(argv[1]==NULL){
		printf("Error! You must specify input filename: ./p2 <input_filename>\n");
		exit(1);
	}
	if((ipt=fopen(argv[1], "r"))==NULL){
		printf("Error! The <filename> file can't be opened\n");
		exit(1);
	}
	opt=fopen("sunrise_c1.out","w");
	while(n!=0){
	int **sunCo;
	int *optimal;
	int **optimalLoc;
	int *optimalUpdate;
	fgets(inBuf, SIZE, ipt);
	sscanf(inBuf, "%d %d %d %d", &dx, &dy, &n, &r);
	optimal = malloc(sizeof(int) * r);
	if(n==0)
		break;
	sunCo = malloc(sizeof(int*) * dx);
	for(i=0; i<dx; i++)
		sunCo[i] = malloc(sizeof(int) * dy);
	
	optimalLoc = malloc(sizeof(int*) * r);
	for(i=0; i<r; i++)
		optimalLoc[i] = malloc(sizeof(int) * 2);
	optimalUpdate = malloc(sizeof(int) * r);

	i=0;
	do{
		
		
		if(n==0)
			break;
		fgets(inBuf, SIZE, ipt);
		sscanf(inBuf, "%d %d", &x, &y);
		i++;	
		sunCo[x-1][y-1] = 1;
			/*add value within index by 1: 0's are "-"'s | 1's are "*"'s | >2's are "$"'s | >500's are @*/
			/*if sunrise place exists, increment by 500. Otherwise, increment by 2 */
	}while(i<n);/*while number of sunrise locations hasn't been reached yet*/
	a=0;
	while(a<r){
		int here[3] = {0,0,0};
		if(n==0)
			break;
		fgets(inBuf, SIZE, ipt);
		sscanf(inBuf, "%d", &z);
		optimal[a] = z;
		optimal_calc(optimal, sunCo, dx, dy, a, here);
		optimalUpdate[a] = here[0];
		optimalLoc[a][0] = here[1];
		optimalLoc[a][1] = here[2];
		a++;
		/*create a 2d array that holds pairs of coordinates for every optimal counter*/
	}/*while number of survey responses hasn't been reached yet*/
	/*
	for(i = 0; i<dx; i++){
		for(a=0; a<dy; a++){
			printf("coordinate: (%d,%d) has value: %d\n",i+1, a+1, sunCo[i][a]);
		}
	}*/
	print_optimal(instanceCount, optimalUpdate, optimalLoc, r, dx, dy, sunCo, opt);	

	for(i=0;i<dx;i++)
		free(sunCo[i]);
	for(i=0; i<r; i++)
		free(optimalLoc[i]);

	free(sunCo);
	free(optimal);
	free(optimalLoc);
	instanceCount++;
	}/*do until 0 0 0 0 is reached*/
	/*the indexes should be added by one to match the description's coordinates*/
	fclose(ipt);
	fclose(opt);
	printf("Output file: sunrise_c1.out created successfully\n");
	return 0;
}
