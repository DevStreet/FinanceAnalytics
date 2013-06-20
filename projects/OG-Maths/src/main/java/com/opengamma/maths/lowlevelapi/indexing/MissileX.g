grammar MissileX;
options {
  output=AST;
  memoize=true;
}

tokens {
  COMMA = ',';
  COLON = ':';
  LEFT_SQUARE_BRACKET = '[';
  RIGHT_SQUARE_BRACKET = ']';
  END = 'end';
  MINUS = '-';
  PLUS = '+';
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

@lexer::header {
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

end_access
  :(END
  |END (WS)* NUMBER);

single_index
  :(WS)* NUMBER (WS)*
  ;

stepper
  :NUMBER COLON
  ;

linear_range // A:B
  :stepper NUMBER
  ;

ended_linear_range
options { backtrack=true; }
  : ((linear_range|NUMBER) COLON end_access)
  | (end_access COLON linear_range);

stepped_range // A:B:C
  :stepper linear_range
  ;

induced_range // {A:B, A:B:C}
  :linear_range
  |stepped_range
  ;

bracketed_induced_range // {[A:B], [A:B:C]}
  :LEFT_SQUARE_BRACKET induced_range RIGHT_SQUARE_BRACKET
  ;

any_range // {[A:B], [A:B:C], A:B, A:B:C}
  :(bracketed_induced_range|induced_range|ended_linear_range);

embedded_range
  options { backtrack=true; }
  :LEFT_SQUARE_BRACKET ((single_index|any_range) COMMA)* (single_index|any_range) RIGHT_SQUARE_BRACKET;
  
compound_embedded
  options { backtrack=true; }
  :(embedded_range COMMA)+ embedded_range;
  
parse_all returns [int value] 
options { backtrack=true; }
    : compound_embedded  {$value=1;}
    | embedded_range  {$value=2;}
    | bracketed_induced_range  {$value=3;}
    | induced_range  {$value=4;}
    | stepped_range  {$value=5;}
    | linear_range  {$value=6;}
    | single_index  {$value=7;}
    | COLON {$value=8;}
    | ended_linear_range {$value=9;}    
    ;
 
/*
 * lexer rules
 */
 
NUMBER  : (PLUS|MINUS)?('0'..'9')+;
WS : (' '|'\r'|'\t'|'\f'|'\n') { $channel=HIDDEN; }; // dump white space elsewhere
