//1.1输出字符
/*#include<iostream.h>
void main()
{
	cout<<"我的第一个C++程序."<<endl;
}*/


//1.2求a和b的和
/*#include<iostream.h>
void main()
{
	int a,b,sum;
	cin>>a>>b;
	sum=a+b;
	cout<<"a和b的和为:"<<sum<<endl;
}*/

//1.3求X和y中的最大者
//第一种
/*#include<iostream.h>
void main()
{
	int x;
	int y;
	int z;
	cin>>x>>y;
	if(x>y)z=x;
	else z=y;
	cout<<z<<endl;
}*/
//第二种(如果max在main后面 要在main中先声明max函数)
/*#include<iostream.h>
int max(int x, int y)
{
	int z;
	if(x>y) z=x;
	else z=y;
	return z;
}
void main()
{
	int a,b,m;
	cin>>a>>b;
	m=max(a,b);
	cout<<m<<endl;
}*/

//1.4包含类的C++程序
/*#include<iostream.h>
class Student
{
private:
	int num;
	int score;
public:
	void setdata()
	{
		cin>>num;
		cin>>score;
	}
	void display()
	{
		cout<<"num="<<num<<endl;
		cout<<"score="<<score<<endl;
	};
};
Student stu1,stu2;
void main()
{
	stu1.setdata();
	stu2.setdata();
	stu1.display();
	stu2.display();
}*/

//计算圆的面积和周长
/*#include<iostream.h>
#define PAI 3.14
void main()
{
	double r ;
	double area;
	double C;
	cout<<"请输入圆的半径:"<<endl;
	cin>>r;
	area=PAI*r*r;
	C=2*PAI*r;
	cout<<"圆的面积是"<<area<<endl;
	cout<<"圆的周长是"<<C<<endl;
}*/

//China加密
/*#include<iostream.h>
void main()
{
	char c1,c2,c3,c4,c5;
	c1='C';
	c2='h';
	c3='i';
	c4='n';
	c5='a';
	c1+=4;
	c2+=4;
	c3+=4;
	c4+=4;
	c5+=4;
	cout<<c1<<c2<<c3<<c4<<c5<<endl;
}*/

//p37习题3
/*#include<iostream.h>
int main()
{
	char c1 = 'a',c2 = 'b', c3 = 'c', c4 = '\101', c5 = '\116';
	cout<<c1<<c2<<c3<<'\n';
	cout<<"\t\b"<<c4<<'\t'<<c5<<'\n';
	return 0;
}*/

//p37习题4
/*#include<iostream.h>
int main()
{
	char c1 = 'C',c2 = '+',c3 = '+';
	cout<<"I say : \""<<c1<<c2<<c3<<'\"';
	cout<<"\t\t"<<"He says : \"C++ is very interesting! \""<<'\n';
	return 0;
}*/


//p16 7
/*#include<iostream.h>
int main()
{
	int a,b,c;
	int f(int x, int y, int z);
	cin>>a>>b>>c;
	c=f(a,b,c);
	cout<<c<<endl;
	return 0;
}
int f(int x, int y , int z)
{
	int m;
	if(x<y) m = x ;
	else m = y;
	if(z<m) m = z ;
	return (m);
}*/
/*#include<iostream.h>
int main()
{
	int a,b;
	int c;
	c=a+b;
	cout<<"a+b="<<a+b;
	return 0;
}*/

//P80 9
/*#include<iostream.h>
void main()
{
	int a,b,c;
	int z1,z2,z3;
	cout<<"请输入第一个数:"<<endl;
	cin>>a;
	cout<<"请输入第二个数:"<<endl;
	cin>>b;
	cout<<"请输入第三个数:"<<endl;
	cin>>c;
	if(a>b&&a>c)
	{
			z1=a;
			if(b>c)
			{
				z2=b;
				z3=c;
				cout<<"从大到小的顺序为:"<<"  "<<z1<<"  "<<z2<<"  "<<z3<<endl;
			}
			else if (c>b)
			{
				z2=c;
				z3=b;
				cout<<"从大到小的顺序为:"<<"  "<<z1<<"  "<<z2<<"  "<<z3<<endl;
			}
			else
		{
			cout<<"检测到值相等的数，请重新输入!"<<endl;
		}
	}
	else if(b>a&&b>c)
	{
		z1=b;
		if(a>c)
		{
			z2=a;
			z3=c;
			cout<<"从大到小的顺序为:"<<"  "<<z1<<"  "<<z2<<"  "<<z3<<endl;
		}
		else if(c>a)
		{
			z2=c;
			z3=a;
			cout<<"从大到小的顺序为:"<<"  "<<z1<<"  "<<z2<<"  "<<z3<<endl;
		}
		else
		{
			cout<<"检测到值相等的数，请重新输入!"<<endl;
		}
	}
	else if(c>a&&c>b)
	{
		z1=c;
		if(a>b)
		{
			z2=a;
			z3=b;
			cout<<"从大到小的顺序为:"<<"  "<<z1<<"  "<<z2<<"  "<<z3<<endl;
		}
		else if(b>a)
		{
			z2=b;
			z3=a;
			cout<<"从大到小的顺序为:"<<"  "<<z1<<"  "<<z2<<"  "<<z3<<endl;
		}
		else
		{
			cout<<"检测到值相等的数，请重新输入!"<<endl;
		}
	}
	else
	{
		cout<<"检测到值相等的数，请重新输入..."<<endl;
	}
}*/

//P80 10
/*#include<iostream.h>
void main()
{
	double x;
	double y;
	cout<<"请输入一个数:"<<endl;
	cin >> x;
	if(x<1)
	{
		y=x;
		cout<<y<<endl;
	}
	else if(x>=1&&x<10)
	{
		y=2*x-1;
		cout<<y<<endl;
	}
	else
	{
		y=3*x-11;
		cout<<y<<endl;
	}
	
}*/

//p81 11
/*#include<iostream.h>
void main()
{
	int x;
	cout<<"请输入成绩:"<<endl;
	cin>>x;
	if(x<=100&&x>=90)
	{
		cout<<"成绩等级为A"<<endl;
	}
	else if(x>=80&&x<=89)
	{
		cout<<"成绩等级为B"<<endl;
	}
	else if(x>=70&&x<=79)
	{
		cout<<"成绩等级为C"<<endl;
	}
	else if(x>=60&&x<=69)
	{
		cout<<"成绩等级为D"<<endl;
	}
	else
	{
		cout<<"成绩等级为E"<<endl;
	}
}*/


//p81 12
/*#include<iostream.h>
void main()
{
	int x;
	int a,b,c,d,e,f;
	cout<<"请输入一个不多于五位的数"<<endl;
	cin>>x;
	if (!(x/10))
	{
		cout<<"这是一个一位数"<<endl<<"值为:"<<x<<endl;
	}
	else if ((x/10)&&!(x/100))
	{
		a=x/10;
		b=x-a*10;
		cout<<"这是一个二位数"<<endl<<"第一个数为:"<<a<<endl<<"第二个数为:"<<b<<endl<<"逆序为:"<<b<<a<<endl;
	}
	else if ((x/100)&&!(x/1000))
	{
		a=x/100;
		b=(x/10)-a*10;
		c=x-a*100-b*10;
		cout<<"这是一个三位数"<<endl<<"第一个数为:"<<a<<endl<<"第二个数为:"<<b<<endl<<"第三个数为:"<<c<<endl<<"逆序为:"<<c<<b<<a<<endl;;
	}
	else if ((x/1000)&&!(x/10000))
	{
		a=x/1000;
		b=(x/100)-a*10;
		c=(x/10)-a*100-b*10;
		d=x-a*1000-b*100-c*10;
		cout<<"这是一个四位数"<<endl<<"第一个数为:"<<a<<endl<<"第二个数为:"<<b<<endl<<"第三个数为:"<<c<<endl<<"第四个数为:"<<d<<endl<<"逆序为:"<<d<<c<<b<<a<<endl;
	}
	else if ((x/10000)&&!(x/100000))
	{
		a=x/10000;
		b=(x/1000)-a*10;
		c=(x/100)-a*100-b*10;
		d=(x/10)-a*1000-b*100-c*10;
		e=x-a*10000-b*1000-c*100-d*10;
		cout<<"这是一个五位数"<<endl<<"第一个数为:"<<a<<endl<<"第二个数为:"<<b<<endl<<"第三个数为:"<<c<<endl<<"第四个数为:"<<d<<endl<<"第五个数为:"<<e<<endl<<"逆序为:"<<e<<d<<c<<b<<a<<endl;
	}
	else
	{
		cout<<"您输入的数超过五位数，请重新输入!";
	}
}*/



//p81 14
/*#include<iostream.h>
void main()
{
	int a,b,c,d,t;
	cin>>a;
	cin>>b;
	cin>>c;
	cin>>d;
	if(a>b)
	{
		t=a;
		a=b;
		b=t;
	}
	if(a>c)
	{
		t=a;
		a=c;
		c=t;
	}
	if(a>d)
	{
		t=a;
		a=d;
		d=t;
	}
	if(b>c)
	{
		t=b;
		b=c;
		c=t;
	}
	if(b>d)
	{
		t=b;
		b=d;
		d=t;
	}
	if(c>d)
	{
		t=c;
		c=d;
		d=t;
	}
	cout<<a<<"  "<<b<<"  "<<c<<"  "<<d<<endl;
}
*/
			

//p81 11
/*#include<iostream.h>
int main()
{
    int score;
    cout<<"请输入成绩：";
    cin>>score;

	switch(score/10)
	{
	     case 10:cout<<"成绩等级为A"<<endl;
	     case 9:cout<<"成绩等级为A"<<endl;break;
         case 8:cout<<"成绩等级为B"<<endl;break;
		 case 7:cout<<"成绩等级为C"<<endl;break;
		 case 6:cout<<"成绩等级为D"<<endl;break;
		 case 5:cout<<"成绩等级为E"<<endl;
		 case 4:cout<<"成绩等级为E"<<endl;
		 case 3:cout<<"成绩等级为E"<<endl;
		 case 2:cout<<"成绩等级为E"<<endl;
		 case 1:cout<<"成绩等级为E"<<endl;
		 case 0:cout<<"成绩等级为E"<<endl;break;

	}
}*/
			


	

