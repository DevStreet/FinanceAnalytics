!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdasum
implicit none
integer:: n, i, incx
parameter (n=5, incx=2)
double precision, dimension(n*incx):: x
double precision:: tmp
double precision:: dasum
external dasum

do i=1,n*incx
	x(i)=i
enddo

tmp = dasum(n,x,incx)

print*,"tmp=",tmp

end program testdasum