// $ANTLR 3.3 Nov 30, 2010 12:50:56 src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g 2013-06-20 16:36:16

/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;
//CSOFF


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class MissileXLexer extends Lexer {
    public static final int EOF=-1;
    public static final int COMMA=4;
    public static final int COLON=5;
    public static final int LEFT_SQUARE_BRACKET=6;
    public static final int RIGHT_SQUARE_BRACKET=7;
    public static final int END=8;
    public static final int MINUS=9;
    public static final int PLUS=10;
    public static final int WS=11;
    public static final int NUMBER=12;

    // delegates
    // delegators

    public MissileXLexer() {;} 
    public MissileXLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public MissileXLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g"; }

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:13:7: ( ',' )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:13:9: ','
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
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:14:7: ( ':' )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:14:9: ':'
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

    // $ANTLR start "LEFT_SQUARE_BRACKET"
    public final void mLEFT_SQUARE_BRACKET() throws RecognitionException {
        try {
            int _type = LEFT_SQUARE_BRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:15:21: ( '[' )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:15:23: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LEFT_SQUARE_BRACKET"

    // $ANTLR start "RIGHT_SQUARE_BRACKET"
    public final void mRIGHT_SQUARE_BRACKET() throws RecognitionException {
        try {
            int _type = RIGHT_SQUARE_BRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:16:22: ( ']' )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:16:24: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RIGHT_SQUARE_BRACKET"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:17:5: ( 'end' )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:17:7: 'end'
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
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:18:7: ( '-' )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:18:9: '-'
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
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:19:6: ( '+' )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:19:8: '+'
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
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:103:9: ( ( PLUS | MINUS )? ( '0' .. '9' )+ )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:103:11: ( PLUS | MINUS )? ( '0' .. '9' )+
            {
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:103:11: ( PLUS | MINUS )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='+'||LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:
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

            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:103:24: ( '0' .. '9' )+
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
            	    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:103:25: '0' .. '9'
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
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:104:4: ( ( ' ' | '\\r' | '\\t' | '\\f' | '\\n' ) )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:104:6: ( ' ' | '\\r' | '\\t' | '\\f' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

             _channel=HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:1:8: ( COMMA | COLON | LEFT_SQUARE_BRACKET | RIGHT_SQUARE_BRACKET | END | MINUS | PLUS | NUMBER | WS )
        int alt3=9;
        alt3 = dfa3.predict(input);
        switch (alt3) {
            case 1 :
                // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:1:10: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 2 :
                // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:1:16: COLON
                {
                mCOLON(); 

                }
                break;
            case 3 :
                // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:1:22: LEFT_SQUARE_BRACKET
                {
                mLEFT_SQUARE_BRACKET(); 

                }
                break;
            case 4 :
                // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:1:42: RIGHT_SQUARE_BRACKET
                {
                mRIGHT_SQUARE_BRACKET(); 

                }
                break;
            case 5 :
                // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:1:63: END
                {
                mEND(); 

                }
                break;
            case 6 :
                // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:1:67: MINUS
                {
                mMINUS(); 

                }
                break;
            case 7 :
                // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:1:73: PLUS
                {
                mPLUS(); 

                }
                break;
            case 8 :
                // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:1:78: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 9 :
                // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:1:85: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\6\uffff\1\12\1\13\4\uffff";
    static final String DFA3_eofS =
        "\14\uffff";
    static final String DFA3_minS =
        "\1\11\5\uffff\2\60\4\uffff";
    static final String DFA3_maxS =
        "\1\145\5\uffff\2\71\4\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\2\uffff\1\10\1\11\1\6\1\7";
    static final String DFA3_specialS =
        "\14\uffff}>";
    static final String[] DFA3_transitionS = {
            "\2\11\1\uffff\2\11\22\uffff\1\11\12\uffff\1\7\1\1\1\6\2\uffff"+
            "\12\10\1\2\40\uffff\1\3\1\uffff\1\4\7\uffff\1\5",
            "",
            "",
            "",
            "",
            "",
            "\12\10",
            "\12\10",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( COMMA | COLON | LEFT_SQUARE_BRACKET | RIGHT_SQUARE_BRACKET | END | MINUS | PLUS | NUMBER | WS );";
        }
    }
 

}