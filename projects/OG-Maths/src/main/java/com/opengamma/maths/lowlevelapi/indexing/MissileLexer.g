//
// Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
// 
// Please see distribution for license.
//
lexer grammar MissileLexer;

options {
  memoize=true;
}

@lexer::header {
/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionParseError;
//CSOFF
}


@members {
  @Override
  public void reportError(RecognitionException e) {
    throw new MathsExceptionParseError(e);
  }
}

/*
 * lexer rules
 */


COMMA : ',';
COLON : ':';
END : 'end';
MINUS : '-';
PLUS : '+';
NUMBER  : (PLUS|MINUS)?('0'..'9')+;
WS : (' '|'\r'|'\t'|'\f'|'\n')+ { $channel=HIDDEN; }; // dump white space elsewhere

START_COMPOUND: '[';
END_COMPOUND: ']';

