//
// Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
// 
// Please see distribution for license.
//
parser grammar MissileParser;
options {
  tokenVocab=MissileLexer;
  output=AST;
}

tokens {
 TOK_TWODINDEX;
 TOK_ALL_COLON;
}

@parser::header {
/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;
//CSOFF
}

/*
 * parser rules
 */

 
single
  :NUMBER
  ;
  
all
  :COLON
  ;  

absolute_end
    : END
    ;

relative_end
    : absolute_end NUMBER
    ;

end_t
    : absolute_end
    | relative_end
    ;

range
    : single COLON^ single
    ;
    

vector
    : (all -> ^(TOK_ALL_COLON))
    | single
    | range
    | (single COLON single COLON single  -> ^(COLON single single single))
    | (single COLON single COLON end_t -> ^(COLON single single end_t))
    | (single COLON end_t -> ^(COLON single end_t))
    ;
 
compound
    : START_COMPOUND! (vector | compound)^  (COMMA^ (vector | compound))* END_COMPOUND!
    ;
   
vector_expr
  : compound
  | vector
  ;  
   
v1
  : vector_expr
  ;    

v2
  : (vector_expr COMMA vector_expr) -> ^(TOK_TWODINDEX vector_expr vector_expr)
  ;
   
index_expr
  : ((v1 COMMA) => v2| v1) // predicate "vector COMMA" and direct to 2D indexer
  ;  

begin
  :index_expr
  ;
  