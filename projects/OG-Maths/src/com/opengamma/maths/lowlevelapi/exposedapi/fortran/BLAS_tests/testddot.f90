!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testddot
implicit none
integer::n,i,incx,incy
parameter(n=10,incx=1,incy=2)
double precision:: x(incx*n)
double precision:: y(n*incy)
double precision:: tmp
double precision:: ddot
external ddot
        
do i = 1,n
        x(i)=i;
        y((i-1)*2+1)=10*((i-1)*2+1);
        y((i-1)*2+2)=10*((i-1)*2+2);
enddo

print*,"x=",x
print*,"y=",y

tmp=0;
tmp=ddot(n,x,incx,y,incy)

print*,"tmp=",tmp

end program testddot