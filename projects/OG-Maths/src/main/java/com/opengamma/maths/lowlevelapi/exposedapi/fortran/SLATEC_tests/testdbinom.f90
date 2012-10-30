!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdbinom
implicit none
double precision, allocatable, dimension(:,:):: answer
integer, parameter:: nlen = 10, scal = 10
integer:: i, j
double precision:: dbinom
external dbinom

!e test a whole pile of ns
allocate(answer(nlen,nlen))

answer=0.d0;

do i = 1,nlen*scal,scal
        do j = 1,i ! don't want to cause error for n<m
                write(*,"(E24.16A)",ADVANCE="NO"),dbinom(i,j),","
        enddo
enddo

end program testdbinom