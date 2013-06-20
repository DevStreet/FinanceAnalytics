// $ANTLR 3.3 Nov 30, 2010 12:50:56 src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g 2013-06-20 16:36:15

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
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

public class MissileXParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMA", "COLON", "LEFT_SQUARE_BRACKET", "RIGHT_SQUARE_BRACKET", "END", "MINUS", "PLUS", "WS", "NUMBER"
    };
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


        public MissileXParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public MissileXParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[21+1];
             
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return MissileXParser.tokenNames; }
    public String getGrammarFileName() { return "src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g"; }


    public static class end_access_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "end_access"
    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:41:1: end_access : ( END | END ( WS )* NUMBER ) ;
    public final MissileXParser.end_access_return end_access() throws RecognitionException {
        MissileXParser.end_access_return retval = new MissileXParser.end_access_return();
        retval.start = input.LT(1);
        int end_access_StartIndex = input.index();
        Object root_0 = null;

        Token END1=null;
        Token END2=null;
        Token WS3=null;
        Token NUMBER4=null;

        Object END1_tree=null;
        Object END2_tree=null;
        Object WS3_tree=null;
        Object NUMBER4_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:42:3: ( ( END | END ( WS )* NUMBER ) )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:42:4: ( END | END ( WS )* NUMBER )
            {
            root_0 = (Object)adaptor.nil();

            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:42:4: ( END | END ( WS )* NUMBER )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==END) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==EOF||(LA2_1>=COMMA && LA2_1<=COLON)||LA2_1==RIGHT_SQUARE_BRACKET) ) {
                    alt2=1;
                }
                else if ( ((LA2_1>=WS && LA2_1<=NUMBER)) ) {
                    alt2=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:42:5: END
                    {
                    END1=(Token)match(input,END,FOLLOW_END_in_end_access121); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    END1_tree = (Object)adaptor.create(END1);
                    adaptor.addChild(root_0, END1_tree);
                    }

                    }
                    break;
                case 2 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:43:4: END ( WS )* NUMBER
                    {
                    END2=(Token)match(input,END,FOLLOW_END_in_end_access126); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    END2_tree = (Object)adaptor.create(END2);
                    adaptor.addChild(root_0, END2_tree);
                    }
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:43:8: ( WS )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==WS) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:43:9: WS
                    	    {
                    	    WS3=(Token)match(input,WS,FOLLOW_WS_in_end_access129); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    WS3_tree = (Object)adaptor.create(WS3);
                    	    adaptor.addChild(root_0, WS3_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_end_access133); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER4_tree = (Object)adaptor.create(NUMBER4);
                    adaptor.addChild(root_0, NUMBER4_tree);
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 1, end_access_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "end_access"

    public static class single_index_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "single_index"
    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:45:1: single_index : ( WS )* NUMBER ( WS )* ;
    public final MissileXParser.single_index_return single_index() throws RecognitionException {
        MissileXParser.single_index_return retval = new MissileXParser.single_index_return();
        retval.start = input.LT(1);
        int single_index_StartIndex = input.index();
        Object root_0 = null;

        Token WS5=null;
        Token NUMBER6=null;
        Token WS7=null;

        Object WS5_tree=null;
        Object NUMBER6_tree=null;
        Object WS7_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:46:3: ( ( WS )* NUMBER ( WS )* )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:46:4: ( WS )* NUMBER ( WS )*
            {
            root_0 = (Object)adaptor.nil();

            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:46:4: ( WS )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==WS) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:46:5: WS
            	    {
            	    WS5=(Token)match(input,WS,FOLLOW_WS_in_single_index144); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    WS5_tree = (Object)adaptor.create(WS5);
            	    adaptor.addChild(root_0, WS5_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_single_index148); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NUMBER6_tree = (Object)adaptor.create(NUMBER6);
            adaptor.addChild(root_0, NUMBER6_tree);
            }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:46:17: ( WS )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==WS) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:46:18: WS
            	    {
            	    WS7=(Token)match(input,WS,FOLLOW_WS_in_single_index151); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    WS7_tree = (Object)adaptor.create(WS7);
            	    adaptor.addChild(root_0, WS7_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 2, single_index_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "single_index"

    public static class stepper_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "stepper"
    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:49:1: stepper : NUMBER COLON ;
    public final MissileXParser.stepper_return stepper() throws RecognitionException {
        MissileXParser.stepper_return retval = new MissileXParser.stepper_return();
        retval.start = input.LT(1);
        int stepper_StartIndex = input.index();
        Object root_0 = null;

        Token NUMBER8=null;
        Token COLON9=null;

        Object NUMBER8_tree=null;
        Object COLON9_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:50:3: ( NUMBER COLON )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:50:4: NUMBER COLON
            {
            root_0 = (Object)adaptor.nil();

            NUMBER8=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_stepper165); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NUMBER8_tree = (Object)adaptor.create(NUMBER8);
            adaptor.addChild(root_0, NUMBER8_tree);
            }
            COLON9=(Token)match(input,COLON,FOLLOW_COLON_in_stepper167); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON9_tree = (Object)adaptor.create(COLON9);
            adaptor.addChild(root_0, COLON9_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 3, stepper_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "stepper"

    public static class linear_range_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "linear_range"
    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:53:1: linear_range : stepper NUMBER ;
    public final MissileXParser.linear_range_return linear_range() throws RecognitionException {
        MissileXParser.linear_range_return retval = new MissileXParser.linear_range_return();
        retval.start = input.LT(1);
        int linear_range_StartIndex = input.index();
        Object root_0 = null;

        Token NUMBER11=null;
        MissileXParser.stepper_return stepper10 = null;


        Object NUMBER11_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:54:3: ( stepper NUMBER )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:54:4: stepper NUMBER
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_stepper_in_linear_range180);
            stepper10=stepper();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, stepper10.getTree());
            NUMBER11=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_linear_range182); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NUMBER11_tree = (Object)adaptor.create(NUMBER11);
            adaptor.addChild(root_0, NUMBER11_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 4, linear_range_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "linear_range"

    public static class ended_linear_range_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ended_linear_range"
    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:57:1: ended_linear_range options {backtrack=true; } : ( ( ( linear_range | NUMBER ) COLON end_access ) | ( end_access COLON linear_range ) );
    public final MissileXParser.ended_linear_range_return ended_linear_range() throws RecognitionException {
        MissileXParser.ended_linear_range_return retval = new MissileXParser.ended_linear_range_return();
        retval.start = input.LT(1);
        int ended_linear_range_StartIndex = input.index();
        Object root_0 = null;

        Token NUMBER13=null;
        Token COLON14=null;
        Token COLON17=null;
        MissileXParser.linear_range_return linear_range12 = null;

        MissileXParser.end_access_return end_access15 = null;

        MissileXParser.end_access_return end_access16 = null;

        MissileXParser.linear_range_return linear_range18 = null;


        Object NUMBER13_tree=null;
        Object COLON14_tree=null;
        Object COLON17_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:59:3: ( ( ( linear_range | NUMBER ) COLON end_access ) | ( end_access COLON linear_range ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==NUMBER) ) {
                alt6=1;
            }
            else if ( (LA6_0==END) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:59:5: ( ( linear_range | NUMBER ) COLON end_access )
                    {
                    root_0 = (Object)adaptor.nil();

                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:59:5: ( ( linear_range | NUMBER ) COLON end_access )
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:59:6: ( linear_range | NUMBER ) COLON end_access
                    {
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:59:6: ( linear_range | NUMBER )
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==NUMBER) ) {
                        int LA5_1 = input.LA(2);

                        if ( (LA5_1==COLON) ) {
                            int LA5_2 = input.LA(3);

                            if ( (LA5_2==NUMBER) ) {
                                alt5=1;
                            }
                            else if ( (LA5_2==END) ) {
                                alt5=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 5, 2, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 5, 1, input);

                            throw nvae;
                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 0, input);

                        throw nvae;
                    }
                    switch (alt5) {
                        case 1 :
                            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:59:7: linear_range
                            {
                            pushFollow(FOLLOW_linear_range_in_ended_linear_range206);
                            linear_range12=linear_range();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, linear_range12.getTree());

                            }
                            break;
                        case 2 :
                            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:59:20: NUMBER
                            {
                            NUMBER13=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_ended_linear_range208); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            NUMBER13_tree = (Object)adaptor.create(NUMBER13);
                            adaptor.addChild(root_0, NUMBER13_tree);
                            }

                            }
                            break;

                    }

                    COLON14=(Token)match(input,COLON,FOLLOW_COLON_in_ended_linear_range211); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON14_tree = (Object)adaptor.create(COLON14);
                    adaptor.addChild(root_0, COLON14_tree);
                    }
                    pushFollow(FOLLOW_end_access_in_ended_linear_range213);
                    end_access15=end_access();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, end_access15.getTree());

                    }


                    }
                    break;
                case 2 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:60:5: ( end_access COLON linear_range )
                    {
                    root_0 = (Object)adaptor.nil();

                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:60:5: ( end_access COLON linear_range )
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:60:6: end_access COLON linear_range
                    {
                    pushFollow(FOLLOW_end_access_in_ended_linear_range221);
                    end_access16=end_access();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, end_access16.getTree());
                    COLON17=(Token)match(input,COLON,FOLLOW_COLON_in_ended_linear_range223); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON17_tree = (Object)adaptor.create(COLON17);
                    adaptor.addChild(root_0, COLON17_tree);
                    }
                    pushFollow(FOLLOW_linear_range_in_ended_linear_range225);
                    linear_range18=linear_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, linear_range18.getTree());

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 5, ended_linear_range_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "ended_linear_range"

    public static class stepped_range_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "stepped_range"
    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:62:1: stepped_range : stepper linear_range ;
    public final MissileXParser.stepped_range_return stepped_range() throws RecognitionException {
        MissileXParser.stepped_range_return retval = new MissileXParser.stepped_range_return();
        retval.start = input.LT(1);
        int stepped_range_StartIndex = input.index();
        Object root_0 = null;

        MissileXParser.stepper_return stepper19 = null;

        MissileXParser.linear_range_return linear_range20 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:63:3: ( stepper linear_range )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:63:4: stepper linear_range
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_stepper_in_stepped_range236);
            stepper19=stepper();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, stepper19.getTree());
            pushFollow(FOLLOW_linear_range_in_stepped_range238);
            linear_range20=linear_range();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, linear_range20.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 6, stepped_range_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "stepped_range"

    public static class induced_range_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "induced_range"
    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:66:1: induced_range : ( linear_range | stepped_range );
    public final MissileXParser.induced_range_return induced_range() throws RecognitionException {
        MissileXParser.induced_range_return retval = new MissileXParser.induced_range_return();
        retval.start = input.LT(1);
        int induced_range_StartIndex = input.index();
        Object root_0 = null;

        MissileXParser.linear_range_return linear_range21 = null;

        MissileXParser.stepped_range_return stepped_range22 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:67:3: ( linear_range | stepped_range )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==NUMBER) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==COLON) ) {
                    int LA7_2 = input.LA(3);

                    if ( (LA7_2==NUMBER) ) {
                        int LA7_3 = input.LA(4);

                        if ( (LA7_3==COLON) ) {
                            alt7=2;
                        }
                        else if ( (LA7_3==EOF||LA7_3==COMMA||LA7_3==RIGHT_SQUARE_BRACKET) ) {
                            alt7=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 7, 3, input);

                            throw nvae;
                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 2, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:67:4: linear_range
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_linear_range_in_induced_range251);
                    linear_range21=linear_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, linear_range21.getTree());

                    }
                    break;
                case 2 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:68:4: stepped_range
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_stepped_range_in_induced_range256);
                    stepped_range22=stepped_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, stepped_range22.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 7, induced_range_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "induced_range"

    public static class bracketed_induced_range_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bracketed_induced_range"
    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:71:1: bracketed_induced_range : LEFT_SQUARE_BRACKET induced_range RIGHT_SQUARE_BRACKET ;
    public final MissileXParser.bracketed_induced_range_return bracketed_induced_range() throws RecognitionException {
        MissileXParser.bracketed_induced_range_return retval = new MissileXParser.bracketed_induced_range_return();
        retval.start = input.LT(1);
        int bracketed_induced_range_StartIndex = input.index();
        Object root_0 = null;

        Token LEFT_SQUARE_BRACKET23=null;
        Token RIGHT_SQUARE_BRACKET25=null;
        MissileXParser.induced_range_return induced_range24 = null;


        Object LEFT_SQUARE_BRACKET23_tree=null;
        Object RIGHT_SQUARE_BRACKET25_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:72:3: ( LEFT_SQUARE_BRACKET induced_range RIGHT_SQUARE_BRACKET )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:72:4: LEFT_SQUARE_BRACKET induced_range RIGHT_SQUARE_BRACKET
            {
            root_0 = (Object)adaptor.nil();

            LEFT_SQUARE_BRACKET23=(Token)match(input,LEFT_SQUARE_BRACKET,FOLLOW_LEFT_SQUARE_BRACKET_in_bracketed_induced_range269); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LEFT_SQUARE_BRACKET23_tree = (Object)adaptor.create(LEFT_SQUARE_BRACKET23);
            adaptor.addChild(root_0, LEFT_SQUARE_BRACKET23_tree);
            }
            pushFollow(FOLLOW_induced_range_in_bracketed_induced_range271);
            induced_range24=induced_range();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, induced_range24.getTree());
            RIGHT_SQUARE_BRACKET25=(Token)match(input,RIGHT_SQUARE_BRACKET,FOLLOW_RIGHT_SQUARE_BRACKET_in_bracketed_induced_range273); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RIGHT_SQUARE_BRACKET25_tree = (Object)adaptor.create(RIGHT_SQUARE_BRACKET25);
            adaptor.addChild(root_0, RIGHT_SQUARE_BRACKET25_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 8, bracketed_induced_range_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "bracketed_induced_range"

    public static class any_range_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "any_range"
    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:75:1: any_range : ( bracketed_induced_range | induced_range | ended_linear_range ) ;
    public final MissileXParser.any_range_return any_range() throws RecognitionException {
        MissileXParser.any_range_return retval = new MissileXParser.any_range_return();
        retval.start = input.LT(1);
        int any_range_StartIndex = input.index();
        Object root_0 = null;

        MissileXParser.bracketed_induced_range_return bracketed_induced_range26 = null;

        MissileXParser.induced_range_return induced_range27 = null;

        MissileXParser.ended_linear_range_return ended_linear_range28 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:76:3: ( ( bracketed_induced_range | induced_range | ended_linear_range ) )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:76:4: ( bracketed_induced_range | induced_range | ended_linear_range )
            {
            root_0 = (Object)adaptor.nil();

            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:76:4: ( bracketed_induced_range | induced_range | ended_linear_range )
            int alt8=3;
            switch ( input.LA(1) ) {
            case LEFT_SQUARE_BRACKET:
                {
                alt8=1;
                }
                break;
            case NUMBER:
                {
                int LA8_2 = input.LA(2);

                if ( (LA8_2==COLON) ) {
                    int LA8_4 = input.LA(3);

                    if ( (LA8_4==NUMBER) ) {
                        int LA8_5 = input.LA(4);

                        if ( (LA8_5==COLON) ) {
                            int LA8_6 = input.LA(5);

                            if ( (LA8_6==NUMBER) ) {
                                alt8=2;
                            }
                            else if ( (LA8_6==END) ) {
                                alt8=3;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 8, 6, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA8_5==COMMA||LA8_5==RIGHT_SQUARE_BRACKET) ) {
                            alt8=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 8, 5, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA8_4==END) ) {
                        alt8=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 4, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 2, input);

                    throw nvae;
                }
                }
                break;
            case END:
                {
                alt8=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:76:5: bracketed_induced_range
                    {
                    pushFollow(FOLLOW_bracketed_induced_range_in_any_range287);
                    bracketed_induced_range26=bracketed_induced_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, bracketed_induced_range26.getTree());

                    }
                    break;
                case 2 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:76:29: induced_range
                    {
                    pushFollow(FOLLOW_induced_range_in_any_range289);
                    induced_range27=induced_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, induced_range27.getTree());

                    }
                    break;
                case 3 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:76:43: ended_linear_range
                    {
                    pushFollow(FOLLOW_ended_linear_range_in_any_range291);
                    ended_linear_range28=ended_linear_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ended_linear_range28.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 9, any_range_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "any_range"

    public static class embedded_range_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "embedded_range"
    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:78:1: embedded_range options {backtrack=true; } : LEFT_SQUARE_BRACKET ( ( single_index | any_range ) COMMA )* ( single_index | any_range ) RIGHT_SQUARE_BRACKET ;
    public final MissileXParser.embedded_range_return embedded_range() throws RecognitionException {
        MissileXParser.embedded_range_return retval = new MissileXParser.embedded_range_return();
        retval.start = input.LT(1);
        int embedded_range_StartIndex = input.index();
        Object root_0 = null;

        Token LEFT_SQUARE_BRACKET29=null;
        Token COMMA32=null;
        Token RIGHT_SQUARE_BRACKET35=null;
        MissileXParser.single_index_return single_index30 = null;

        MissileXParser.any_range_return any_range31 = null;

        MissileXParser.single_index_return single_index33 = null;

        MissileXParser.any_range_return any_range34 = null;


        Object LEFT_SQUARE_BRACKET29_tree=null;
        Object COMMA32_tree=null;
        Object RIGHT_SQUARE_BRACKET35_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:80:3: ( LEFT_SQUARE_BRACKET ( ( single_index | any_range ) COMMA )* ( single_index | any_range ) RIGHT_SQUARE_BRACKET )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:80:4: LEFT_SQUARE_BRACKET ( ( single_index | any_range ) COMMA )* ( single_index | any_range ) RIGHT_SQUARE_BRACKET
            {
            root_0 = (Object)adaptor.nil();

            LEFT_SQUARE_BRACKET29=(Token)match(input,LEFT_SQUARE_BRACKET,FOLLOW_LEFT_SQUARE_BRACKET_in_embedded_range312); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LEFT_SQUARE_BRACKET29_tree = (Object)adaptor.create(LEFT_SQUARE_BRACKET29);
            adaptor.addChild(root_0, LEFT_SQUARE_BRACKET29_tree);
            }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:80:24: ( ( single_index | any_range ) COMMA )*
            loop10:
            do {
                int alt10=2;
                alt10 = dfa10.predict(input);
                switch (alt10) {
            	case 1 :
            	    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:80:25: ( single_index | any_range ) COMMA
            	    {
            	    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:80:25: ( single_index | any_range )
            	    int alt9=2;
            	    switch ( input.LA(1) ) {
            	    case WS:
            	        {
            	        alt9=1;
            	        }
            	        break;
            	    case NUMBER:
            	        {
            	        int LA9_2 = input.LA(2);

            	        if ( (LA9_2==COLON) ) {
            	            alt9=2;
            	        }
            	        else if ( (LA9_2==COMMA||LA9_2==WS) ) {
            	            alt9=1;
            	        }
            	        else {
            	            if (state.backtracking>0) {state.failed=true; return retval;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 9, 2, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case LEFT_SQUARE_BRACKET:
            	    case END:
            	        {
            	        alt9=2;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 9, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt9) {
            	        case 1 :
            	            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:80:26: single_index
            	            {
            	            pushFollow(FOLLOW_single_index_in_embedded_range316);
            	            single_index30=single_index();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, single_index30.getTree());

            	            }
            	            break;
            	        case 2 :
            	            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:80:39: any_range
            	            {
            	            pushFollow(FOLLOW_any_range_in_embedded_range318);
            	            any_range31=any_range();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, any_range31.getTree());

            	            }
            	            break;

            	    }

            	    COMMA32=(Token)match(input,COMMA,FOLLOW_COMMA_in_embedded_range321); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA32_tree = (Object)adaptor.create(COMMA32);
            	    adaptor.addChild(root_0, COMMA32_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:80:58: ( single_index | any_range )
            int alt11=2;
            switch ( input.LA(1) ) {
            case WS:
                {
                alt11=1;
                }
                break;
            case NUMBER:
                {
                int LA11_2 = input.LA(2);

                if ( (LA11_2==COLON) ) {
                    alt11=2;
                }
                else if ( (LA11_2==RIGHT_SQUARE_BRACKET||LA11_2==WS) ) {
                    alt11=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 2, input);

                    throw nvae;
                }
                }
                break;
            case LEFT_SQUARE_BRACKET:
            case END:
                {
                alt11=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:80:59: single_index
                    {
                    pushFollow(FOLLOW_single_index_in_embedded_range326);
                    single_index33=single_index();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, single_index33.getTree());

                    }
                    break;
                case 2 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:80:72: any_range
                    {
                    pushFollow(FOLLOW_any_range_in_embedded_range328);
                    any_range34=any_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, any_range34.getTree());

                    }
                    break;

            }

            RIGHT_SQUARE_BRACKET35=(Token)match(input,RIGHT_SQUARE_BRACKET,FOLLOW_RIGHT_SQUARE_BRACKET_in_embedded_range331); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RIGHT_SQUARE_BRACKET35_tree = (Object)adaptor.create(RIGHT_SQUARE_BRACKET35);
            adaptor.addChild(root_0, RIGHT_SQUARE_BRACKET35_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 10, embedded_range_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "embedded_range"

    public static class compound_embedded_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "compound_embedded"
    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:82:1: compound_embedded options {backtrack=true; } : ( embedded_range COMMA )+ embedded_range ;
    public final MissileXParser.compound_embedded_return compound_embedded() throws RecognitionException {
        MissileXParser.compound_embedded_return retval = new MissileXParser.compound_embedded_return();
        retval.start = input.LT(1);
        int compound_embedded_StartIndex = input.index();
        Object root_0 = null;

        Token COMMA37=null;
        MissileXParser.embedded_range_return embedded_range36 = null;

        MissileXParser.embedded_range_return embedded_range38 = null;


        Object COMMA37_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:84:3: ( ( embedded_range COMMA )+ embedded_range )
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:84:4: ( embedded_range COMMA )+ embedded_range
            {
            root_0 = (Object)adaptor.nil();

            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:84:4: ( embedded_range COMMA )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                alt12 = dfa12.predict(input);
                switch (alt12) {
            	case 1 :
            	    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:84:5: embedded_range COMMA
            	    {
            	    pushFollow(FOLLOW_embedded_range_in_compound_embedded354);
            	    embedded_range36=embedded_range();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, embedded_range36.getTree());
            	    COMMA37=(Token)match(input,COMMA,FOLLOW_COMMA_in_compound_embedded356); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA37_tree = (Object)adaptor.create(COMMA37);
            	    adaptor.addChild(root_0, COMMA37_tree);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);

            pushFollow(FOLLOW_embedded_range_in_compound_embedded360);
            embedded_range38=embedded_range();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, embedded_range38.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 11, compound_embedded_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "compound_embedded"

    public static class parse_all_return extends ParserRuleReturnScope {
        public int value;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parse_all"
    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:86:1: parse_all returns [int value] options {backtrack=true; } : ( compound_embedded | embedded_range | bracketed_induced_range | induced_range | stepped_range | linear_range | single_index | COLON | ended_linear_range );
    public final MissileXParser.parse_all_return parse_all() throws RecognitionException {
        MissileXParser.parse_all_return retval = new MissileXParser.parse_all_return();
        retval.start = input.LT(1);
        int parse_all_StartIndex = input.index();
        Object root_0 = null;

        Token COLON46=null;
        MissileXParser.compound_embedded_return compound_embedded39 = null;

        MissileXParser.embedded_range_return embedded_range40 = null;

        MissileXParser.bracketed_induced_range_return bracketed_induced_range41 = null;

        MissileXParser.induced_range_return induced_range42 = null;

        MissileXParser.stepped_range_return stepped_range43 = null;

        MissileXParser.linear_range_return linear_range44 = null;

        MissileXParser.single_index_return single_index45 = null;

        MissileXParser.ended_linear_range_return ended_linear_range47 = null;


        Object COLON46_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }
            // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:88:5: ( compound_embedded | embedded_range | bracketed_induced_range | induced_range | stepped_range | linear_range | single_index | COLON | ended_linear_range )
            int alt13=9;
            alt13 = dfa13.predict(input);
            switch (alt13) {
                case 1 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:88:7: compound_embedded
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_compound_embedded_in_parse_all388);
                    compound_embedded39=compound_embedded();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_embedded39.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value =1;
                    }

                    }
                    break;
                case 2 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:89:7: embedded_range
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_embedded_range_in_parse_all399);
                    embedded_range40=embedded_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, embedded_range40.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value =2;
                    }

                    }
                    break;
                case 3 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:90:7: bracketed_induced_range
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_bracketed_induced_range_in_parse_all410);
                    bracketed_induced_range41=bracketed_induced_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, bracketed_induced_range41.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value =3;
                    }

                    }
                    break;
                case 4 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:91:7: induced_range
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_induced_range_in_parse_all421);
                    induced_range42=induced_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, induced_range42.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value =4;
                    }

                    }
                    break;
                case 5 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:92:7: stepped_range
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_stepped_range_in_parse_all432);
                    stepped_range43=stepped_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, stepped_range43.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value =5;
                    }

                    }
                    break;
                case 6 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:93:7: linear_range
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_linear_range_in_parse_all443);
                    linear_range44=linear_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, linear_range44.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value =6;
                    }

                    }
                    break;
                case 7 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:94:7: single_index
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_single_index_in_parse_all454);
                    single_index45=single_index();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, single_index45.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value =7;
                    }

                    }
                    break;
                case 8 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:95:7: COLON
                    {
                    root_0 = (Object)adaptor.nil();

                    COLON46=(Token)match(input,COLON,FOLLOW_COLON_in_parse_all465); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON46_tree = (Object)adaptor.create(COLON46);
                    adaptor.addChild(root_0, COLON46_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value =8;
                    }

                    }
                    break;
                case 9 :
                    // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:96:7: ended_linear_range
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ended_linear_range_in_parse_all475);
                    ended_linear_range47=ended_linear_range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ended_linear_range47.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value =9;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 12, parse_all_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "parse_all"

    // $ANTLR start synpred3_MissileX
    public final void synpred3_MissileX_fragment() throws RecognitionException {   
        // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:89:7: ( embedded_range )
        // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:89:7: embedded_range
        {
        pushFollow(FOLLOW_embedded_range_in_synpred3_MissileX399);
        embedded_range();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_MissileX

    // $ANTLR start synpred4_MissileX
    public final void synpred4_MissileX_fragment() throws RecognitionException {   
        // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:90:7: ( bracketed_induced_range )
        // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:90:7: bracketed_induced_range
        {
        pushFollow(FOLLOW_bracketed_induced_range_in_synpred4_MissileX410);
        bracketed_induced_range();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_MissileX

    // $ANTLR start synpred5_MissileX
    public final void synpred5_MissileX_fragment() throws RecognitionException {   
        // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:91:7: ( induced_range )
        // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:91:7: induced_range
        {
        pushFollow(FOLLOW_induced_range_in_synpred5_MissileX421);
        induced_range();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_MissileX

    // $ANTLR start synpred7_MissileX
    public final void synpred7_MissileX_fragment() throws RecognitionException {   
        // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:93:7: ( linear_range )
        // src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g:93:7: linear_range
        {
        pushFollow(FOLLOW_linear_range_in_synpred7_MissileX443);
        linear_range();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred7_MissileX

    // Delegated rules

    public final boolean synpred4_MissileX() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_MissileX_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_MissileX() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_MissileX_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_MissileX() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_MissileX_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_MissileX() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_MissileX_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA10 dfa10 = new DFA10(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA10_eotS =
        "\34\uffff";
    static final String DFA10_eofS =
        "\34\uffff";
    static final String DFA10_minS =
        "\1\6\1\13\1\4\1\14\1\5\1\4\1\10\1\4\2\uffff\1\5\1\14\1\13\1\5\2"+
        "\4\1\14\1\5\1\10\1\13\1\4\1\5\1\14\1\4\1\14\2\4\1\7";
    static final String DFA10_maxS =
        "\2\14\1\13\2\14\1\13\1\14\1\13\2\uffff\1\5\2\14\1\5\1\7\2\14\1\5"+
        "\2\14\2\7\1\14\1\7\1\14\3\7";
    static final String DFA10_acceptS =
        "\10\uffff\1\2\1\1\22\uffff";
    static final String DFA10_specialS =
        "\34\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\3\1\uffff\1\4\2\uffff\1\1\1\2",
            "\1\1\1\5",
            "\1\11\1\6\1\uffff\1\10\3\uffff\1\7",
            "\1\12",
            "\1\13\5\uffff\1\14\1\15",
            "\1\11\2\uffff\1\10\3\uffff\1\7",
            "\1\17\3\uffff\1\16",
            "\1\11\2\uffff\1\10\3\uffff\1\7",
            "",
            "",
            "\1\20",
            "\1\21",
            "\1\14\1\15",
            "\1\13",
            "\1\11\1\22\1\uffff\1\10",
            "\1\11\2\uffff\1\10\3\uffff\1\23\1\24",
            "\1\25",
            "\1\26",
            "\1\17\3\uffff\1\27",
            "\1\23\1\24",
            "\1\11\2\uffff\1\10",
            "\1\30\1\uffff\1\31",
            "\1\32",
            "\1\11\2\uffff\1\10",
            "\1\33",
            "\1\11\2\uffff\1\10",
            "\1\11\2\uffff\1\10",
            "\1\31"
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "()* loopback of 80:24: ( ( single_index | any_range ) COMMA )*";
        }
    }
    static final String DFA12_eotS =
        "\37\uffff";
    static final String DFA12_eofS =
        "\12\uffff\1\21\24\uffff";
    static final String DFA12_minS =
        "\2\6\1\13\1\4\1\14\1\5\1\4\1\10\1\4\1\6\1\4\1\5\1\14\1\13\1\5\2"+
        "\4\2\uffff\1\14\1\5\1\10\1\13\1\4\1\5\1\14\1\4\1\14\2\4\1\7";
    static final String DFA12_maxS =
        "\1\6\2\14\1\13\2\14\1\13\1\14\1\13\1\14\1\4\1\5\2\14\1\5\1\7\1\14"+
        "\2\uffff\1\14\1\5\2\14\2\7\1\14\1\7\1\14\3\7";
    static final String DFA12_acceptS =
        "\21\uffff\1\2\1\1\14\uffff";
    static final String DFA12_specialS =
        "\37\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\1",
            "\1\4\1\uffff\1\5\2\uffff\1\2\1\3",
            "\1\2\1\6",
            "\1\11\1\7\1\uffff\1\12\3\uffff\1\10",
            "\1\13",
            "\1\14\5\uffff\1\15\1\16",
            "\1\11\2\uffff\1\12\3\uffff\1\10",
            "\1\20\3\uffff\1\17",
            "\1\11\2\uffff\1\12\3\uffff\1\10",
            "\1\4\1\uffff\1\5\2\uffff\1\2\1\3",
            "\1\22",
            "\1\23",
            "\1\24",
            "\1\15\1\16",
            "\1\14",
            "\1\11\1\25\1\uffff\1\12",
            "\1\11\2\uffff\1\12\3\uffff\1\26\1\27",
            "",
            "",
            "\1\30",
            "\1\31",
            "\1\20\3\uffff\1\32",
            "\1\26\1\27",
            "\1\11\2\uffff\1\12",
            "\1\33\1\uffff\1\34",
            "\1\35",
            "\1\11\2\uffff\1\12",
            "\1\36",
            "\1\11\2\uffff\1\12",
            "\1\11\2\uffff\1\12",
            "\1\34"
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "()+ loopback of 84:4: ( embedded_range COMMA )+";
        }
    }
    static final String DFA13_eotS =
        "\60\uffff";
    static final String DFA13_eofS =
        "\2\uffff\1\3\14\uffff\1\31\40\uffff";
    static final String DFA13_minS =
        "\1\5\1\6\1\5\3\uffff\1\13\1\4\1\14\1\5\1\10\1\4\1\10\1\4\1\6\1\4"+
        "\1\5\1\14\1\13\2\5\3\4\2\uffff\1\14\1\5\1\10\2\uffff\1\10\1\4\1"+
        "\13\1\4\1\10\1\5\1\14\1\uffff\1\4\1\uffff\1\4\1\14\2\4\1\10\1\7"+
        "\1\4";
    static final String DFA13_maxS =
        "\2\14\1\13\3\uffff\1\14\1\13\3\14\1\13\1\14\1\13\1\14\1\4\1\5\2"+
        "\14\2\5\1\7\1\14\1\13\2\uffff\1\14\1\5\1\14\2\uffff\1\14\1\4\1\14"+
        "\1\7\1\14\1\7\1\14\1\uffff\1\7\1\uffff\1\7\1\14\2\7\1\14\2\7";
    static final String DFA13_acceptS =
        "\3\uffff\1\7\1\10\1\11\22\uffff\1\1\1\2\3\uffff\1\4\1\6\7\uffff"+
        "\1\5\1\uffff\1\3\7\uffff";
    static final String DFA13_specialS =
        "\24\uffff\1\0\13\uffff\1\1\17\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\4\1\1\1\uffff\1\5\2\uffff\1\3\1\2",
            "\1\10\1\uffff\1\11\2\uffff\1\6\1\7",
            "\1\12\5\uffff\1\3",
            "",
            "",
            "",
            "\1\6\1\13",
            "\1\16\1\14\1\uffff\1\17\3\uffff\1\15",
            "\1\20",
            "\1\21\5\uffff\1\22\1\23",
            "\1\5\3\uffff\1\24",
            "\1\16\2\uffff\1\17\3\uffff\1\15",
            "\1\26\3\uffff\1\25",
            "\1\16\2\uffff\1\17\3\uffff\1\15",
            "\1\10\1\uffff\1\11\2\uffff\1\6\1\27",
            "\1\30",
            "\1\32",
            "\1\33",
            "\1\22\1\23",
            "\1\21",
            "\1\34",
            "\1\16\1\37\1\uffff\1\40",
            "\1\16\2\uffff\1\17\3\uffff\1\41\1\42",
            "\1\16\1\43\1\uffff\1\17\3\uffff\1\15",
            "",
            "",
            "\1\44",
            "\1\45",
            "\1\5\3\uffff\1\46",
            "",
            "",
            "\1\26\3\uffff\1\47",
            "\1\30",
            "\1\41\1\42",
            "\1\16\2\uffff\1\17",
            "\1\26\3\uffff\1\51",
            "\1\52\1\uffff\1\53",
            "\1\54",
            "",
            "\1\16\2\uffff\1\40",
            "",
            "\1\16\1\55\1\uffff\1\17",
            "\1\56",
            "\1\16\2\uffff\1\17",
            "\1\16\2\uffff\1\17",
            "\1\26\3\uffff\1\57",
            "\1\53",
            "\1\16\2\uffff\1\17"
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "86:1: parse_all returns [int value] options {backtrack=true; } : ( compound_embedded | embedded_range | bracketed_induced_range | induced_range | stepped_range | linear_range | single_index | COLON | ended_linear_range );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_20 = input.LA(1);

                         
                        int index13_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA13_20==COLON) ) {s = 28;}

                        else if ( (synpred5_MissileX()) ) {s = 29;}

                        else if ( (synpred7_MissileX()) ) {s = 30;}

                         
                        input.seek(index13_20);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA13_32 = input.LA(1);

                         
                        int index13_32 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA13_32==COMMA) ) {s = 24;}

                        else if ( (synpred3_MissileX()) ) {s = 25;}

                        else if ( (synpred4_MissileX()) ) {s = 40;}

                         
                        input.seek(index13_32);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_END_in_end_access121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_END_in_end_access126 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_WS_in_end_access129 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_NUMBER_in_end_access133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_single_index144 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_NUMBER_in_single_index148 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_WS_in_single_index151 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_NUMBER_in_stepper165 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COLON_in_stepper167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stepper_in_linear_range180 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_NUMBER_in_linear_range182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_linear_range_in_ended_linear_range206 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ended_linear_range208 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COLON_in_ended_linear_range211 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_end_access_in_ended_linear_range213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_end_access_in_ended_linear_range221 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COLON_in_ended_linear_range223 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_linear_range_in_ended_linear_range225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stepper_in_stepped_range236 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_linear_range_in_stepped_range238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_linear_range_in_induced_range251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stepped_range_in_induced_range256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_BRACKET_in_bracketed_induced_range269 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_induced_range_in_bracketed_induced_range271 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_BRACKET_in_bracketed_induced_range273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bracketed_induced_range_in_any_range287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_induced_range_in_any_range289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ended_linear_range_in_any_range291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_BRACKET_in_embedded_range312 = new BitSet(new long[]{0x0000000000001940L});
    public static final BitSet FOLLOW_single_index_in_embedded_range316 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_any_range_in_embedded_range318 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_COMMA_in_embedded_range321 = new BitSet(new long[]{0x0000000000001940L});
    public static final BitSet FOLLOW_single_index_in_embedded_range326 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_any_range_in_embedded_range328 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_BRACKET_in_embedded_range331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_embedded_range_in_compound_embedded354 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_COMMA_in_compound_embedded356 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_embedded_range_in_compound_embedded360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_embedded_in_parse_all388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_embedded_range_in_parse_all399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bracketed_induced_range_in_parse_all410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_induced_range_in_parse_all421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stepped_range_in_parse_all432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_linear_range_in_parse_all443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_single_index_in_parse_all454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_parse_all465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ended_linear_range_in_parse_all475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_embedded_range_in_synpred3_MissileX399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bracketed_induced_range_in_synpred4_MissileX410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_induced_range_in_synpred5_MissileX421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_linear_range_in_synpred7_MissileX443 = new BitSet(new long[]{0x0000000000000002L});

}