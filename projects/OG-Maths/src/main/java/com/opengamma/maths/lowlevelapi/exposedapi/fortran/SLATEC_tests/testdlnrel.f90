!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdlnrel
implicit none
double precision, allocatable, dimension(:):: x, answer
integer, parameter:: vlen = 17
integer:: i
double precision:: start
double precision:: dlnrel
external dlnrel

!e need to test x's in -3.75000E-01 to  3.75000E-01, out of this range intrinsic log is called
allocate(x(vlen), answer(vlen))
start=-0.4d0;
do i=1,vlen
	x(i)=start+(i-1)*0.05d0;
enddo

do i=1,vlen
	answer(i)=dlnrel(x(i))
enddo

print*,"x=",x
print*,"answer=",answer

end program testdlnrel