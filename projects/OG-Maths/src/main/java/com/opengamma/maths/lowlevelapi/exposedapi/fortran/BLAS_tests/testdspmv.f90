!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdspmv
implicit none
integer::  n, incx, incy
parameter(n=5, incx=1,incy=1)
integer:: j, i, ptr
double precision:: A(n,n), AP(((n*(n+1))/2)), x(n), y(n), alpha, beta
character(2):: length
external dspmv
write(length,"(I2)")((n*(n+1))/2)

!e some data for A
do j = 1,n
        do i = 1,n
                A(i,j) = (i-1)*n+j
        enddo
enddo

AP=-1;
!e UPLO = "U"
ptr=1
do j = 1, n
        do i = 1,j
                AP(ptr) = A( i, j )
                ptr=ptr+1
        enddo
enddo

!e set x
do i = 1,n
        x(i)=i;
enddo

!e set y
do i = 1,n
        y(i)=10*((i-1)*2+2);
enddo

print*,"input"
print*,"x=",x
print*,"y=",y
print*,""
print*,""
do i=1,n
        write(*,"(A3)",ADVANCE="NO")"A ="
        write(*,"("//trim(adjustl(length))//"(2X,F5.2))"),A(i,:)
enddo
print*,""
print*,""
write(*,"(A3)",ADVANCE="NO")"AP="
write(*,"("//trim(adjustl(length))//"(2X,F5.2))"),AP(:)


alpha=2d0
beta=5d0

call  dspmv("U",n,alpha,AP,x,incx,beta,y,incy)

print*,""
print*,"output"
write(*,"(A2)")"y="
write(*,"("//trim(adjustl(length))//"(2X,F8.2))"),y

end program testdspmv