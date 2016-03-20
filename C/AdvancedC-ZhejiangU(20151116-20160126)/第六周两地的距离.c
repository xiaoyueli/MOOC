#include <stdio.h>

struct {
	char *name;
	int miles[12];
}form[] = {
	{"Atlanta", 	 {0, 1108, 708, 1430, 732, 791, 2191, 663, 854, 748, 2483, 2625}},
	{"Boston", 		 {1108, 0, 994, 1998, 799, 1830, 3017, 1520, 222, 315, 3128, 3016}},
	{"Chicago", 	 {708, 994, 0, 1021, 279, 1091, 2048, 1397, 809, 785, 2173, 2052}},
	{"Denver", 		 {1430, 1998, 1021, 0, 1283, 1034, 1031, 2107, 1794, 1739, 1255, 1341}},
	{"Detroit",      {732, 799, 279, 1283, 0, 1276, 2288, 1385, 649, 609, 2399, 2327}},
    {"Houston",      {791, 1830, 1091, 1034, 1276, 0, 1541, 1190, 1610, 1511, 1911, 2369}},
    {"Los Angeles",  {2191, 3017, 2048, 1031, 2288, 1541, 0, 2716, 2794, 2703, 387, 1134}},
    {"Miami",        {663, 1520, 1397, 2107, 1385, 1190, 2716, 0, 1334, 1230, 3093, 3303}},
    {"New York",     {854, 222, 809, 1794, 649, 1610, 2794, 1334, 0, 101, 2930, 2841}},
    {"Philadelphia", {748, 315, 785, 1739, 609, 1511, 2703, 1230, 101, 0, 2902, 2816}},
    {"San Francisco",{2483, 3128, 2173, 1255, 2399, 1911, 387, 3093, 2930, 2902, 0, 810}},
    {"Seattle",      {2625, 3016, 2052, 1341, 2327, 2369, 1134, 3303, 2841, 2816, 810, 0}},
	
};


int getMiles(char* c1, char* c2)
{
	int i, miles=-1, index1=-1, index2=-1;
	for(i = 0 ; i < sizeof(form)/sizeof(form[0]); i++)
	{
		if(form[i].name == c1)
		{
			index1 = i;
		}else if(form[i].name == c2)
		{
			index2 = i;
		}
	}
	if(index1 != -1 && index2 != -1)
	{
		miles = form[index1].miles[index2];
	}
	return miles;
}

int main()
{
	char* city1 = "Philadelphia";
	char* city2 = "Houston";
	int miles = getMiles(city1, city2);
	if(miles == -1)
	{
		printf("the cities are not found.");
	}else
	{
		printf("%s is %d miles far from %s.", city1, miles, city2);
	}
	return 1;
}
