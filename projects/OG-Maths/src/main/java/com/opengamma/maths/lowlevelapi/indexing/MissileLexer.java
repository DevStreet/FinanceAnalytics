// $ANTLR 3.3 Nov 30, 2010 12:50:56 MissileLexer.g 2013-07-02 13:08:04

/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionParseError;
//CSOFF


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class MissileLexer extends Lexer {
    public static final int EOF=-1;
    public static final int COMMA=4;
    public static final int COLON=5;
    public static final int END=6;
    public static final int MINUS=7;
    public static final int PLUS=8;
    public static final int NUMBER=9;
    public static final int WS=10;
    public static final int START_COMPOUND=11;
    public static final int END_COMPOUND=12;

      @Override
      public void reportError(RecognitionException e) {
        throw new MathsExceptionParseError(e);
      }


    // delegates
    // delegators

    public MissileLexer() {;} 
    public MissileLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public MissileLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "MissileLexer.g"; }

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        int COMMA_StartIndex = input.index();
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return ; }
            // MissileLexer.g:37:7: ( ',' )
            // MissileLexer.g:37:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        int COLON_StartIndex = input.index();
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return ; }
            // MissileLexer.g:38:7: ( ':' )
            // MissileLexer.g:38:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        int END_StartIndex = input.index();
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return ; }
            // MissileLexer.g:39:5: ( 'end' )
            // MissileLexer.g:39:7: 'end'
            {
            match("end"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        int MINUS_StartIndex = input.index();
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return ; }
            // MissileLexer.g:40:7: ( '-' )
            // MissileLexer.g:40:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        int PLUS_StartIndex = input.index();
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return ; }
            // MissileLexer.g:41:6: ( '+' )
            // MissileLexer.g:41:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        int NUMBER_StartIndex = input.index();
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return ; }
            // MissileLexer.g:42:9: ( ( PLUS | MINUS )? ( '0' .. '9' )+ )
            // MissileLexer.g:42:11: ( PLUS | MINUS )? ( '0' .. '9' )+
            {
            // MissileLexer.g:42:11: ( PLUS | MINUS )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='+'||LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // MissileLexer.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // MissileLexer.g:42:24: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // MissileLexer.g:42:25: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        int WS_StartIndex = input.index();
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return ; }
            // MissileLexer.g:43:4: ( ( ' ' | '\\r' | '\\t' | '\\f' | '\\n' )+ )
            // MissileLexer.g:43:6: ( ' ' | '\\r' | '\\t' | '\\f' | '\\n' )+
            {
            // MissileLexer.g:43:6: ( ' ' | '\\r' | '\\t' | '\\f' | '\\n' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\t' && LA3_0<='\n')||(LA3_0>='\f' && LA3_0<='\r')||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // MissileLexer.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

             _channel=HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "START_COMPOUND"
    public final void mSTART_COMPOUND() throws RecognitionException {
        int START_COMPOUND_StartIndex = input.index();
        try {
            int _type = START_COMPOUND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return ; }
            // MissileLexer.g:45:15: ( '[' )
            // MissileLexer.g:45:17: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "START_COMPOUND"

    // $ANTLR start "END_COMPOUND"
    public final void mEND_COMPOUND() throws RecognitionException {
        int END_COMPOUND_StartIndex = input.index();
        try {
            int _type = END_COMPOUND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return ; }
            // MissileLexer.g:46:13: ( ']' )
            // MissileLexer.g:46:15: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END_COMPOUND"

    public void mTokens() throws RecognitionException {
        // MissileLexer.g:1:8: ( COMMA | COLON | END | MINUS | PLUS | NUMBER | WS | START_COMPOUND | END_COMPOUND )
        int alt4=9;
        alt4 = dfa4.predict(input);
        switch (alt4) {
            case 1 :
                // MissileLexer.g:1:10: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 2 :
                // MissileLexer.g:1:16: COLON
                {
                mCOLON(); 

                }
                break;
            case 3 :
                // MissileLexer.g:1:22: END
                {
                mEND(); 

                }
                break;
            case 4 :
                // MissileLexer.g:1:26: MINUS
                {
                mMINUS(); 

                }
                break;
            case 5 :
                // MissileLexer.g:1:32: PLUS
                {
                mPLUS(); 

                }
                break;
            case 6 :
                // MissileLexer.g:1:37: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 7 :
                // MissileLexer.g:1:44: WS
                {
                mWS(); 

                }
                break;
            case 8 :
                // MissileLexer.g:1:47: START_COMPOUND
                {
                mSTART_COMPOUND(); 

                }
                break;
            case 9 :
                // MissileLexer.g:1:62: END_COMPOUND
                {
                mEND_COMPOUND(); 

                }
                break;

        }

    }


    protected DFA4 dfa4 = new DFA4(this);
    static final String DFA4_eotS =
        "\4\uffff\1\12\1\13\6\uffff";
    static final String DFA4_eofS =
        "\14\uffff";
    static final String DFA4_minS =
        "\1\11\3\uffff\2\60\6\uffff";
    static final String DFA4_maxS =
        "\1\145\3\uffff\2\71\6\uffff";
    static final String DFA4_acceptS =
        "\1\uffff\1\1\1\2\1\3\2\uffff\1\6\1\7\1\10\1\11\1\4\1\5";
    static final String DFA4_specialS =
        "\14\uffff}>";
    static final String[] DFA4_transitionS = {
            "\2\7\1\uffff\2\7\22\uffff\1\7\12\uffff\1\5\1\1\1\4\2\uffff\12"+
            "\6\1\2\40\uffff\1\10\1\uffff\1\11\7\uffff\1\3",
            "",
            "",
            "",
            "\12\6",
            "\12\6",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( COMMA | COLON | END | MINUS | PLUS | NUMBER | WS | START_COMPOUND | END_COMPOUND );";
        }
    }
 

}