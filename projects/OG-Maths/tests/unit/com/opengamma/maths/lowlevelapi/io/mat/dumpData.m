clear all; close all; clc;

A=reshape(1:12,3,4)';

save('A.mat','A');

B=reshape(uint32(1:12),3,4)';
save('B.mat','B');

C = single(7.);
save('C.mat','C');

save('AB.mat','A','B');
save('ABC.mat','A','B','C');