!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdrotg
implicit none
double precision::a, b, c, s
double precision::acpy, bcpy

external drotg;


a = 0.34d0
b = 0.79d0
c=0
s=0
acpy=a
bcpy=b

call drotg(a,b,c,s)

print*,"acpy=",acpy,"bcpy=",bcpy,"c=",c,"s=",s

print*,"| c  s| * |a| = |r|"
print*,"|-s  c|   |b|   |0|"

print*,"c*acpy + s*bcpy = ",(c*acpy + s*bcpy)
print*,"(-s)*acpy + c*bcpy = ",((-s)*acpy + c*bcpy)

print*,"a, b",a,b


end program testdrotg

