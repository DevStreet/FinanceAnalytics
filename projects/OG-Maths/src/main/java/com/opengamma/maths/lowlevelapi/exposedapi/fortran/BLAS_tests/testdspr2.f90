!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdspr2
implicit none
integer::  n, incx, incy, lda
parameter(n=5, incx=1,incy=2, lda=n)
integer:: j, i, ptr
double precision:: A(n,n), AP(((n*(n+1))/2)), x(n), y(2*n), alpha
character(2):: length1, length2
external dspr2
write(length1,"(I2)")n
write(length2,"(I2)")((n*(n+1))/2)

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

!e set x & y
do i = 1,n
        x(i)=i;
        y(i)=10*i
        y(i+n)=10*(i+n)
enddo

print*,"input"
print*,"x=",x
print*,"y=",y
print*,""
print*,""
do i=1,n
        write(*,"(A3)",ADVANCE="NO")"A ="
        write(*,"("//trim(adjustl(length1))//"(2X,F5.2))"),A(i,:)
enddo
print*,""
print*,""
write(*,"(A3)",ADVANCE="NO")"AP="
write(*,"("//trim(adjustl(length2))//"(2X,F5.2))"),AP(:)

alpha=2d0

call  dspr2("U",n,alpha,x,incx,y,incy,AP,lda)

print*,""
print*,"output"
write(*,"(A3)",ADVANCE="NO")"AP="
write(*,"("//trim(adjustl(length2))//"(2X,F8.2))"),AP(:)


end program testdspr2