!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testidamax
implicit none
integer(kind=4):: n, incx, i
double precision:: x(10)
character(2):: length
integer:: idamax, tmp
external idamax

x(1)=1
x(2)=2
x(3)=3
x(4)=4
x(5)=5
x(6)=6
x(7)=71
x(8)=8
x(9)=9
x(10)=10

n=5
incx=2
print*,"input"
print*,"x=",x,n,incx

tmp = idamax(n,x,incx)

print*,""
print*,"output"
print*,tmp
print*,x((tmp-1)*incx+1)
end program testidamax