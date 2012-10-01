!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testd9lgmc
implicit none
double precision, allocatable, dimension(:):: x, answer
integer, parameter:: ten = 10d0
integer, parameter:: vlen = 9
integer:: i
double precision:: d9lgmc
external d9lgmc

!e test a whole pile of x's
allocate(x(vlen), answer(vlen))
do i=1,vlen
	x(i)=ten**i
enddo

do i=1,vlen
	answer(i)=d9lgmc(x(i))
enddo

print*,"x=",x
print*,"answer=",answer

print*,"going to underflow"
print*,d9lgmc(1.d307)

end program testd9lgmc