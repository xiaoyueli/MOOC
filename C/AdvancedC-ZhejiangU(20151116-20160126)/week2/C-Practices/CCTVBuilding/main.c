#include "acllib.h"
#include <stdio.h>

void plant(int x1, int y1, int x2, int y2, ACL_Brush_Style style, ACL_Color color )
{
	setPenWidth(1);
	setPenColor(BLACK);
	setBrushColor(color);
	setBrushStyle(style);
	ellipse(x1, y1, x2, y2);
}

void bird(int x1, int y1, ACL_Brush_Style style, ACL_Color bodyColor, ACL_Color eyeColor)
{
	setPenWidth(1);
	setPenColor(BLACK);
	setBrushColor(bodyColor);
	setBrushStyle(style);
	int x2, y2;
	x2= x1 + 25;
	y2= y1 + 25;
	ellipse(x1, y1, x2, y2);
	int x, y;
	x= (x1 + x2)/2;
	y= (y1 + y2)/2;
	moveTo(x1, y);//mouth 
	lineRel(0, -2);
	lineRel(-4, 2);
	lineRel(4, 2);
	lineRel(0, -2);
	moveTo(x, y1);//wing 
	lineRel(-3, 0);
	lineRel(3, -6);
	lineRel(3, 6);
	lineRel(-3, 0);
	moveTo(x, y2);//wing 
	lineRel(-3, 0);
	lineRel(3, 6);
	lineRel(3, -6);
	lineRel(-3, 0);
	moveTo(x2, y);//tail 
	lineRel(3, -5);
	moveTo(x2, y);
	lineRel(4, -6);
	moveTo(x2, y);
	lineRel(5, -5);
	moveTo(x-8, y);//eyes
	setPenColor(eyeColor);
	lineRel(-1, -1);
	lineRel(3, 3);
	moveTo(x-8, y);
	lineRel(1, -1);
	lineRel(-3, 3);

}

void wall(const POINT* points, int n, ACL_Brush_Style style, ACL_Color color)
{

	setBrushStyle(style);
	setBrushColor(color);
	polygon(points, n);

}

void cctvBuliding(int x, int y)
{
	setPenWidth(2);
	setPenColor(BLACK);
	setPenColor(BLACK);
	int x1= x, y1= y;//p1
	int x2= x1 + 20, y2 = y1- 250;//p2
	line(x1,y1, x2, y2);
	moveTo(x2, y2);
	lineRel(135, 2); // p3
	int x3, y3;
	x3= getX();
	y3= getY();
	lineRel(6, 63);//p4
	int x4, y4;
	x4= getX();
	y4= getY();
	lineRel(-74, 4);//p5
	int x5, y5;
	x5= getX();
	y5= getY();
	int x6= x1 + 70, y6 = y1 + 5;//p6
	lineTo(x6, y6);
	lineTo(x1, y1);//p1
	moveTo(x6, y6);//p6
	int x7= x6 + 17, y7= y6 - 5; //p7
	lineTo(x7, y7);
	int x8=x5+16, y8= y5+10;
	lineTo(x8, y8);//p8
	lineTo(x5, y5);//p5
	moveTo(x8, y8);//p8
	lineRel(32, -1);//p9
	int x9, y9;
	x9= getX();
	y9= getY();
	lineRel(10,10);//p10
	int x10, y10;
	x10= getX();
	y10= getY();
	lineRel(37, -2);//p11
	int x11, y11;
	x11= getX();
	y11= getY();
	lineTo(x4, y4);//p4
	moveTo(x3, y3);//p3
	lineRel(33, 55);//p12
	int x12, y12;
	x12= getX();
	y12= getY(); 
	lineRel(30, 185);//p13
	int x13, y13;
	x13= getX();
	y13= getY();
	lineRel(-10, 4);//p14
	int x14, y14;
	x14 = getX();
	y14 = getY();
	lineTo(x11, y11);//p11
	moveTo(x10, y10);//p10
	lineRel(20, 157);//p15
	int x15, y15;
	x15 = getX();
	y15 = getY();	
	lineTo(x14, y14);//p14
	int x16 = x7, y16 = y7 - 4;//p16
	moveTo(x16, y16);
	lineRel(20, -6);//p17
	int x17, y17;
	x17 = getX();
	y17 = getY();
	lineRel(3, -35);//p18
	int x18, y18;
	x18 = getX();
	y18 = getY();
	lineRel(-19, -2);//p19
	int x19, y19;
	x19 = getX();
	y19 = getY();
	moveTo(x18, y18);//p18
	lineRel(51, -1);//p20
	int x20, y20;
	x20 = getX();
	y20 = getY();
	int x21= x15, y21= y15-3;
	moveTo(x21, y21);//p21
	lineTo(x17, y17);//p17
	

	POINT p1[]={   //p1,p2,p3,p4,p5,p6
		{x1, y1},
		{x2, y2},
		{x3, y3},
		{x4, y4},
		{x5, y5},
		{x6, y6},
	};
	
	POINT p2[]={   //p5,p6,p7,p8
		{x5, y5},
		{x6, y6},
		{x7, y7},
		{x8, y8},
	};	
	
	POINT p3[]={   //p5,p8,p9,p10,p11,p4
		{x5, y5},
		{x8, y8},
		{x9, y9},
		{x10, y10},
		{x11, y11},
		{x4, y4}
	};
	
	POINT p4[]={   //p11,p4,p3,p12,p13,p14
		{x11, y11},
		{x4, y4},
		{x3, y3},
		{x12, y12},
		{x13, y13},
		{x14, y14},
	};
	
	POINT p5[]={   //p11,p14,p20,p10
		{x11, y11},
		{x14, y14},
		{x15, y15},
		{x10, y10},
	};
	
	POINT p6[]={   //p16,p17,p18,p19
		{x16, y16},
		{x17, y17},
		{x18, y18},
		{x19, y19},
	};
	
	POINT p7[]={   //p17,p18,p20,p21
		{x17, y17},
		{x18, y18},
		{x20, y20},
		{x21, y21},
	};
	
	wall(p1, sizeof(p1)/sizeof(p1[0]),BRUSH_STYLE_DIAGCROSS,RGB(28, 17, 68));
	wall(p2, sizeof(p2)/sizeof(p2[0]),BRUSH_STYLE_FDIAGONAL,RGB(73, 73, 73));
	wall(p3, sizeof(p3)/sizeof(p3[0]),BRUSH_STYLE_SOLID,RGB(130, 130, 130));
	wall(p4, sizeof(p4)/sizeof(p4[0]),BRUSH_STYLE_FDIAGONAL,RGB(73, 73, 73));
	wall(p5, sizeof(p5)/sizeof(p5[0]),BRUSH_STYLE_DIAGCROSS,RGB(28, 17, 68));
	wall(p6, sizeof(p6)/sizeof(p6[0]),BRUSH_STYLE_SOLID,RGB(130, 130, 130));
	wall(p7, sizeof(p7)/sizeof(p7[0]),BRUSH_STYLE_CROSS,RGB(73, 73, 73));
	

}

int Setup()
{
	int width = 400;
	int height = 600;
	initWindow("CCTV building", 300, 200, width, height);
	beginPaint();
	int i, x, y;
	int num =100;
	for(i=0; i<= num; i++)
	{
		x=(int)(rand()%width);
		y=(int)(rand()%(height - 120));
		bird(x, y, BRUSH_STYLE_SOLID, BLACK, WHITE );

	}
	plant(0, 470, 400, 600, BRUSH_STYLE_SOLID, RGB(100, 100, 100));
	plant(20, 470, 380, 550, BRUSH_STYLE_SOLID, BLACK);
	cctvBuliding(80, 500);
	plant(60, 470, 110, 510, BRUSH_STYLE_SOLID, WHITE);
	plant(80, 460, 135, 510, BRUSH_STYLE_SOLID, RGB(105, 227, 64));
	plant(105, 473, 155, 515, BRUSH_STYLE_SOLID,  WHITE);
	plant(135, 485, 175, 517, BRUSH_STYLE_SOLID,  WHITE);
	plant(250, 490, 280, 515, BRUSH_STYLE_SOLID,  WHITE);
	plant(295, 493, 320, 513, BRUSH_STYLE_SOLID,  WHITE);
	plant(270, 485, 305, 515, BRUSH_STYLE_SOLID, RGB(105, 227, 64));
	bird(50, 165, BRUSH_STYLE_SOLID,  RED, BLACK );
	bird(135, 175, BRUSH_STYLE_SOLID,  BLUE , BLACK);
	bird(185, 175, BRUSH_STYLE_SOLID,  YELLOW, BLACK);
	bird(235, 175, BRUSH_STYLE_SOLID,  GREEN , BLACK);
	bird(285, 190, BRUSH_STYLE_SOLID,  MAGENTA , BLACK);

	endPaint();
	return 0; 
}
