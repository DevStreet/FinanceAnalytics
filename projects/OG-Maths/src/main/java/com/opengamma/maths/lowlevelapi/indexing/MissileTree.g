//
// Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
// 
// Please see distribution for license.
//
tree grammar MissileTree;
options {
    tokenVocab=MissileParser;
    ASTLabelType = CommonTree;
}


@header {
/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

//CSOFF
}
 
walk returns [Index idx]
     : (a=test2D)
     {$idx=$a.idx;}
     | (b=test1D)
     {$idx=$b.idx;} 
     ;
 
test2D returns [TwoDIndex idx]
      : ^(TOK_TWODINDEX a=vector b=vector)
      {$idx = new TwoDIndex($a.items, $b.items);}
      ;

test1D returns [OneDIndex idx]
     : (a=vector)
     {$idx = new OneDIndex($a.items);}
     ;

vector returns [List<IndexItem> items = new ArrayList<IndexItem>(); ]
  :(a=expr)
  {$items.addAll($a.itemlist);}
  ; 
 
expr returns [List<IndexItem> itemlist]
    options {backtrack=true;}
    : 
    {itemlist  = new ArrayList<IndexItem>();}
    ^(
         (COMMA
         |COLON
         |(val=tok_number)
         {$itemlist.add($val.item);}
         |(val=all_colon)
         {$itemlist.add($val.item);}
         )
         (a=any_valid_subexpr
         {$itemlist.add($a.item);}
         |b=expr
         {$itemlist.addAll($b.itemlist);}
         )*
         
       )
    ;
    
any_valid_subexpr  returns [IndexItem item]
  options {backtrack=true;}
  :(a=computable_range)
  {$item = $a.item;}
  |(a=stepped_computable_range)
  {$item = $a.item;}
  |(a=offset_ended_stepped_range)
  {$item = $a.item;}
  |(a=offset_ended_range)
  {$item = $a.item;}
  |(a=ended_stepped_range)
  {$item = $a.item;}
  |(a=ended_range)
  {$item = $a.item;}
  ;
      
    
computable_range returns [IndexItem item]
    :(a=NUMBER b=NUMBER) {$item = new LinearIndex(Integer.valueOf($a.text),Integer.valueOf($b.text));}
    ;
    
stepped_computable_range returns [IndexItem item] 
    :(a=NUMBER b=NUMBER c=NUMBER)
    {$item = new LinearIndex(Integer.valueOf($a.text),Integer.valueOf($b.text),Integer.valueOf($c.text));}
    ;        

ended_range returns [IndexItem item]
    :(a=NUMBER END)
    {$item = new EndIndex(Integer.valueOf($a.text));}
    ;

ended_stepped_range returns [IndexItem item]
    :(a=NUMBER b=NUMBER END)
    {$item = new EndIndex(Integer.valueOf($a.text),Integer.valueOf($b.text));}
    ;

offset_ended_range returns [IndexItem item]
    :(a=NUMBER END b=NUMBER)
    {$item = new EndIndex(Integer.valueOf($a.text),1,Integer.valueOf($b.text));}
    ;

offset_ended_stepped_range returns [IndexItem item]
    :(a=NUMBER b=NUMBER END c=NUMBER)
    {$item = new EndIndex(Integer.valueOf($a.text),Integer.valueOf($b.text),Integer.valueOf($c.text));}
    ;

tok_number returns [IndexItem item]
  :(a=NUMBER)
  {$item = new GeneralIndex(Integer.valueOf($a.text));}
  ;

all_colon returns [IndexItem item]
  :TOK_ALL_COLON
    {$item = ColonIndex.getInstance();}
  ;
    