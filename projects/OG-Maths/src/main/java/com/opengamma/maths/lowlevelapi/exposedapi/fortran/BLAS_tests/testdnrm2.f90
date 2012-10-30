!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdnrm2
implicit none
integer:: n, incx, i
parameter(n=5,incx=2)
double precision:: x(incx*n)
character(2):: length
double precision:: dnrm2, tmp
external dnrm2

do i = 1,n*incx
        x(i)=i;
enddo

print*,"input"
print*,"x=",x

tmp = dnrm2(n,x,incx)

print*,""
print*,"output"
print*,tmp
end program testdnrm2