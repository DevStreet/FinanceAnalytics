// $ANTLR 3.3 Nov 30, 2010 12:50:56 MissileParser.g 2013-07-02 13:08:04

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

public class MissileParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMA", "COLON", "END", "MINUS", "PLUS", "NUMBER", "WS", "START_COMPOUND", "END_COMPOUND", "TOK_TWODINDEX", "TOK_ALL_COLON"
    };
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
    public static final int TOK_TWODINDEX=13;
    public static final int TOK_ALL_COLON=14;

    // delegates
    // delegators


        public MissileParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public MissileParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return MissileParser.tokenNames; }
    public String getGrammarFileName() { return "MissileParser.g"; }


    public static class single_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "single"
    // MissileParser.g:32:1: single : NUMBER ;
    public final MissileParser.single_return single() throws RecognitionException {
        MissileParser.single_return retval = new MissileParser.single_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NUMBER1=null;

        Object NUMBER1_tree=null;

        try {
            // MissileParser.g:33:3: ( NUMBER )
            // MissileParser.g:33:4: NUMBER
            {
            root_0 = (Object)adaptor.nil();

            NUMBER1=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_single63); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NUMBER1_tree = (Object)adaptor.create(NUMBER1);
            adaptor.addChild(root_0, NUMBER1_tree);
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
        }
        return retval;
    }
    // $ANTLR end "single"

    public static class all_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "all"
    // MissileParser.g:36:1: all : COLON ;
    public final MissileParser.all_return all() throws RecognitionException {
        MissileParser.all_return retval = new MissileParser.all_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON2=null;

        Object COLON2_tree=null;

        try {
            // MissileParser.g:37:3: ( COLON )
            // MissileParser.g:37:4: COLON
            {
            root_0 = (Object)adaptor.nil();

            COLON2=(Token)match(input,COLON,FOLLOW_COLON_in_all77); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON2_tree = (Object)adaptor.create(COLON2);
            adaptor.addChild(root_0, COLON2_tree);
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
        }
        return retval;
    }
    // $ANTLR end "all"

    public static class absolute_end_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "absolute_end"
    // MissileParser.g:40:1: absolute_end : END ;
    public final MissileParser.absolute_end_return absolute_end() throws RecognitionException {
        MissileParser.absolute_end_return retval = new MissileParser.absolute_end_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token END3=null;

        Object END3_tree=null;

        try {
            // MissileParser.g:41:5: ( END )
            // MissileParser.g:41:7: END
            {
            root_0 = (Object)adaptor.nil();

            END3=(Token)match(input,END,FOLLOW_END_in_absolute_end94); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            END3_tree = (Object)adaptor.create(END3);
            adaptor.addChild(root_0, END3_tree);
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
        }
        return retval;
    }
    // $ANTLR end "absolute_end"

    public static class relative_end_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relative_end"
    // MissileParser.g:44:1: relative_end : absolute_end NUMBER ;
    public final MissileParser.relative_end_return relative_end() throws RecognitionException {
        MissileParser.relative_end_return retval = new MissileParser.relative_end_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NUMBER5=null;
        MissileParser.absolute_end_return absolute_end4 = null;


        Object NUMBER5_tree=null;

        try {
            // MissileParser.g:45:5: ( absolute_end NUMBER )
            // MissileParser.g:45:7: absolute_end NUMBER
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_absolute_end_in_relative_end111);
            absolute_end4=absolute_end();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, absolute_end4.getTree());
            NUMBER5=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_relative_end113); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NUMBER5_tree = (Object)adaptor.create(NUMBER5);
            adaptor.addChild(root_0, NUMBER5_tree);
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
        }
        return retval;
    }
    // $ANTLR end "relative_end"

    public static class end_t_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "end_t"
    // MissileParser.g:48:1: end_t : ( absolute_end | relative_end );
    public final MissileParser.end_t_return end_t() throws RecognitionException {
        MissileParser.end_t_return retval = new MissileParser.end_t_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        MissileParser.absolute_end_return absolute_end6 = null;

        MissileParser.relative_end_return relative_end7 = null;



        try {
            // MissileParser.g:49:5: ( absolute_end | relative_end )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==END) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==EOF||LA1_1==COMMA||LA1_1==END_COMPOUND) ) {
                    alt1=1;
                }
                else if ( (LA1_1==NUMBER) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // MissileParser.g:49:7: absolute_end
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_absolute_end_in_end_t130);
                    absolute_end6=absolute_end();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, absolute_end6.getTree());

                    }
                    break;
                case 2 :
                    // MissileParser.g:50:7: relative_end
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_relative_end_in_end_t138);
                    relative_end7=relative_end();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, relative_end7.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "end_t"

    public static class range_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "range"
    // MissileParser.g:53:1: range : single COLON single ;
    public final MissileParser.range_return range() throws RecognitionException {
        MissileParser.range_return retval = new MissileParser.range_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON9=null;
        MissileParser.single_return single8 = null;

        MissileParser.single_return single10 = null;


        Object COLON9_tree=null;

        try {
            // MissileParser.g:54:5: ( single COLON single )
            // MissileParser.g:54:7: single COLON single
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_single_in_range155);
            single8=single();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, single8.getTree());
            COLON9=(Token)match(input,COLON,FOLLOW_COLON_in_range157); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON9_tree = (Object)adaptor.create(COLON9);
            root_0 = (Object)adaptor.becomeRoot(COLON9_tree, root_0);
            }
            pushFollow(FOLLOW_single_in_range160);
            single10=single();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, single10.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "range"

    public static class vector_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "vector"
    // MissileParser.g:58:1: vector : ( ( all -> ^( TOK_ALL_COLON ) ) | single | range | ( single COLON single COLON single -> ^( COLON single single single ) ) | ( single COLON single COLON end_t -> ^( COLON single single end_t ) ) | ( single COLON end_t -> ^( COLON single end_t ) ) );
    public final MissileParser.vector_return vector() throws RecognitionException {
        MissileParser.vector_return retval = new MissileParser.vector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON15=null;
        Token COLON17=null;
        Token COLON20=null;
        Token COLON22=null;
        Token COLON25=null;
        MissileParser.all_return all11 = null;

        MissileParser.single_return single12 = null;

        MissileParser.range_return range13 = null;

        MissileParser.single_return single14 = null;

        MissileParser.single_return single16 = null;

        MissileParser.single_return single18 = null;

        MissileParser.single_return single19 = null;

        MissileParser.single_return single21 = null;

        MissileParser.end_t_return end_t23 = null;

        MissileParser.single_return single24 = null;

        MissileParser.end_t_return end_t26 = null;


        Object COLON15_tree=null;
        Object COLON17_tree=null;
        Object COLON20_tree=null;
        Object COLON22_tree=null;
        Object COLON25_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleSubtreeStream stream_single=new RewriteRuleSubtreeStream(adaptor,"rule single");
        RewriteRuleSubtreeStream stream_end_t=new RewriteRuleSubtreeStream(adaptor,"rule end_t");
        RewriteRuleSubtreeStream stream_all=new RewriteRuleSubtreeStream(adaptor,"rule all");
        try {
            // MissileParser.g:59:5: ( ( all -> ^( TOK_ALL_COLON ) ) | single | range | ( single COLON single COLON single -> ^( COLON single single single ) ) | ( single COLON single COLON end_t -> ^( COLON single single end_t ) ) | ( single COLON end_t -> ^( COLON single end_t ) ) )
            int alt2=6;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // MissileParser.g:59:7: ( all -> ^( TOK_ALL_COLON ) )
                    {
                    // MissileParser.g:59:7: ( all -> ^( TOK_ALL_COLON ) )
                    // MissileParser.g:59:8: all
                    {
                    pushFollow(FOLLOW_all_in_vector183);
                    all11=all();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_all.add(all11.getTree());


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 59:12: -> ^( TOK_ALL_COLON )
                    {
                        // MissileParser.g:59:15: ^( TOK_ALL_COLON )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TOK_ALL_COLON, "TOK_ALL_COLON"), root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }


                    }
                    break;
                case 2 :
                    // MissileParser.g:60:7: single
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_single_in_vector198);
                    single12=single();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, single12.getTree());

                    }
                    break;
                case 3 :
                    // MissileParser.g:61:7: range
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_range_in_vector206);
                    range13=range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, range13.getTree());

                    }
                    break;
                case 4 :
                    // MissileParser.g:62:7: ( single COLON single COLON single -> ^( COLON single single single ) )
                    {
                    // MissileParser.g:62:7: ( single COLON single COLON single -> ^( COLON single single single ) )
                    // MissileParser.g:62:8: single COLON single COLON single
                    {
                    pushFollow(FOLLOW_single_in_vector215);
                    single14=single();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_single.add(single14.getTree());
                    COLON15=(Token)match(input,COLON,FOLLOW_COLON_in_vector217); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON15);

                    pushFollow(FOLLOW_single_in_vector219);
                    single16=single();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_single.add(single16.getTree());
                    COLON17=(Token)match(input,COLON,FOLLOW_COLON_in_vector221); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON17);

                    pushFollow(FOLLOW_single_in_vector223);
                    single18=single();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_single.add(single18.getTree());


                    // AST REWRITE
                    // elements: single, COLON, single, single
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 62:42: -> ^( COLON single single single )
                    {
                        // MissileParser.g:62:45: ^( COLON single single single )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_COLON.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_single.nextTree());
                        adaptor.addChild(root_1, stream_single.nextTree());
                        adaptor.addChild(root_1, stream_single.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }


                    }
                    break;
                case 5 :
                    // MissileParser.g:63:7: ( single COLON single COLON end_t -> ^( COLON single single end_t ) )
                    {
                    // MissileParser.g:63:7: ( single COLON single COLON end_t -> ^( COLON single single end_t ) )
                    // MissileParser.g:63:8: single COLON single COLON end_t
                    {
                    pushFollow(FOLLOW_single_in_vector246);
                    single19=single();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_single.add(single19.getTree());
                    COLON20=(Token)match(input,COLON,FOLLOW_COLON_in_vector248); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON20);

                    pushFollow(FOLLOW_single_in_vector250);
                    single21=single();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_single.add(single21.getTree());
                    COLON22=(Token)match(input,COLON,FOLLOW_COLON_in_vector252); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON22);

                    pushFollow(FOLLOW_end_t_in_vector254);
                    end_t23=end_t();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_end_t.add(end_t23.getTree());


                    // AST REWRITE
                    // elements: single, end_t, COLON, single
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 63:40: -> ^( COLON single single end_t )
                    {
                        // MissileParser.g:63:43: ^( COLON single single end_t )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_COLON.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_single.nextTree());
                        adaptor.addChild(root_1, stream_single.nextTree());
                        adaptor.addChild(root_1, stream_end_t.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }


                    }
                    break;
                case 6 :
                    // MissileParser.g:64:7: ( single COLON end_t -> ^( COLON single end_t ) )
                    {
                    // MissileParser.g:64:7: ( single COLON end_t -> ^( COLON single end_t ) )
                    // MissileParser.g:64:8: single COLON end_t
                    {
                    pushFollow(FOLLOW_single_in_vector276);
                    single24=single();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_single.add(single24.getTree());
                    COLON25=(Token)match(input,COLON,FOLLOW_COLON_in_vector278); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON25);

                    pushFollow(FOLLOW_end_t_in_vector280);
                    end_t26=end_t();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_end_t.add(end_t26.getTree());


                    // AST REWRITE
                    // elements: COLON, single, end_t
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 64:27: -> ^( COLON single end_t )
                    {
                        // MissileParser.g:64:30: ^( COLON single end_t )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_COLON.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_single.nextTree());
                        adaptor.addChild(root_1, stream_end_t.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
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
        }
        return retval;
    }
    // $ANTLR end "vector"

    public static class compound_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "compound"
    // MissileParser.g:67:1: compound : START_COMPOUND ( vector | compound ) ( COMMA ( vector | compound ) )* END_COMPOUND ;
    public final MissileParser.compound_return compound() throws RecognitionException {
        MissileParser.compound_return retval = new MissileParser.compound_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token START_COMPOUND27=null;
        Token COMMA30=null;
        Token END_COMPOUND33=null;
        MissileParser.vector_return vector28 = null;

        MissileParser.compound_return compound29 = null;

        MissileParser.vector_return vector31 = null;

        MissileParser.compound_return compound32 = null;


        Object START_COMPOUND27_tree=null;
        Object COMMA30_tree=null;
        Object END_COMPOUND33_tree=null;

        try {
            // MissileParser.g:68:5: ( START_COMPOUND ( vector | compound ) ( COMMA ( vector | compound ) )* END_COMPOUND )
            // MissileParser.g:68:7: START_COMPOUND ( vector | compound ) ( COMMA ( vector | compound ) )* END_COMPOUND
            {
            root_0 = (Object)adaptor.nil();

            START_COMPOUND27=(Token)match(input,START_COMPOUND,FOLLOW_START_COMPOUND_in_compound309); if (state.failed) return retval;
            // MissileParser.g:68:23: ( vector | compound )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==COLON||LA3_0==NUMBER) ) {
                alt3=1;
            }
            else if ( (LA3_0==START_COMPOUND) ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // MissileParser.g:68:24: vector
                    {
                    pushFollow(FOLLOW_vector_in_compound313);
                    vector28=vector();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, vector28.getTree());

                    }
                    break;
                case 2 :
                    // MissileParser.g:68:33: compound
                    {
                    pushFollow(FOLLOW_compound_in_compound317);
                    compound29=compound();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compound29.getTree());

                    }
                    break;

            }

            // MissileParser.g:68:45: ( COMMA ( vector | compound ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==COMMA) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // MissileParser.g:68:46: COMMA ( vector | compound )
            	    {
            	    COMMA30=(Token)match(input,COMMA,FOLLOW_COMMA_in_compound323); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA30_tree = (Object)adaptor.create(COMMA30);
            	    root_0 = (Object)adaptor.becomeRoot(COMMA30_tree, root_0);
            	    }
            	    // MissileParser.g:68:53: ( vector | compound )
            	    int alt4=2;
            	    int LA4_0 = input.LA(1);

            	    if ( (LA4_0==COLON||LA4_0==NUMBER) ) {
            	        alt4=1;
            	    }
            	    else if ( (LA4_0==START_COMPOUND) ) {
            	        alt4=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 4, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt4) {
            	        case 1 :
            	            // MissileParser.g:68:54: vector
            	            {
            	            pushFollow(FOLLOW_vector_in_compound327);
            	            vector31=vector();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, vector31.getTree());

            	            }
            	            break;
            	        case 2 :
            	            // MissileParser.g:68:63: compound
            	            {
            	            pushFollow(FOLLOW_compound_in_compound331);
            	            compound32=compound();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, compound32.getTree());

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            END_COMPOUND33=(Token)match(input,END_COMPOUND,FOLLOW_END_COMPOUND_in_compound336); if (state.failed) return retval;

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
        }
        return retval;
    }
    // $ANTLR end "compound"

    public static class vector_expr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "vector_expr"
    // MissileParser.g:71:1: vector_expr : ( compound | vector );
    public final MissileParser.vector_expr_return vector_expr() throws RecognitionException {
        MissileParser.vector_expr_return retval = new MissileParser.vector_expr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        MissileParser.compound_return compound34 = null;

        MissileParser.vector_return vector35 = null;



        try {
            // MissileParser.g:72:3: ( compound | vector )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==START_COMPOUND) ) {
                alt6=1;
            }
            else if ( (LA6_0==COLON||LA6_0==NUMBER) ) {
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
                    // MissileParser.g:72:5: compound
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_compound_in_vector_expr355);
                    compound34=compound();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compound34.getTree());

                    }
                    break;
                case 2 :
                    // MissileParser.g:73:5: vector
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_vector_in_vector_expr361);
                    vector35=vector();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, vector35.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "vector_expr"

    public static class v1_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "v1"
    // MissileParser.g:76:1: v1 : vector_expr ;
    public final MissileParser.v1_return v1() throws RecognitionException {
        MissileParser.v1_return retval = new MissileParser.v1_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        MissileParser.vector_expr_return vector_expr36 = null;



        try {
            // MissileParser.g:77:3: ( vector_expr )
            // MissileParser.g:77:5: vector_expr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_vector_expr_in_v1379);
            vector_expr36=vector_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, vector_expr36.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "v1"

    public static class v2_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "v2"
    // MissileParser.g:80:1: v2 : ( vector_expr COMMA vector_expr ) -> ^( TOK_TWODINDEX vector_expr vector_expr ) ;
    public final MissileParser.v2_return v2() throws RecognitionException {
        MissileParser.v2_return retval = new MissileParser.v2_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA38=null;
        MissileParser.vector_expr_return vector_expr37 = null;

        MissileParser.vector_expr_return vector_expr39 = null;


        Object COMMA38_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_vector_expr=new RewriteRuleSubtreeStream(adaptor,"rule vector_expr");
        try {
            // MissileParser.g:81:3: ( ( vector_expr COMMA vector_expr ) -> ^( TOK_TWODINDEX vector_expr vector_expr ) )
            // MissileParser.g:81:5: ( vector_expr COMMA vector_expr )
            {
            // MissileParser.g:81:5: ( vector_expr COMMA vector_expr )
            // MissileParser.g:81:6: vector_expr COMMA vector_expr
            {
            pushFollow(FOLLOW_vector_expr_in_v2397);
            vector_expr37=vector_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_vector_expr.add(vector_expr37.getTree());
            COMMA38=(Token)match(input,COMMA,FOLLOW_COMMA_in_v2399); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COMMA.add(COMMA38);

            pushFollow(FOLLOW_vector_expr_in_v2401);
            vector_expr39=vector_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_vector_expr.add(vector_expr39.getTree());

            }



            // AST REWRITE
            // elements: vector_expr, vector_expr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 81:37: -> ^( TOK_TWODINDEX vector_expr vector_expr )
            {
                // MissileParser.g:81:40: ^( TOK_TWODINDEX vector_expr vector_expr )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TOK_TWODINDEX, "TOK_TWODINDEX"), root_1);

                adaptor.addChild(root_1, stream_vector_expr.nextTree());
                adaptor.addChild(root_1, stream_vector_expr.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        }
        return retval;
    }
    // $ANTLR end "v2"

    public static class index_expr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "index_expr"
    // MissileParser.g:84:1: index_expr : ( ( v1 COMMA )=> v2 | v1 ) ;
    public final MissileParser.index_expr_return index_expr() throws RecognitionException {
        MissileParser.index_expr_return retval = new MissileParser.index_expr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        MissileParser.v2_return v240 = null;

        MissileParser.v1_return v141 = null;



        try {
            // MissileParser.g:85:3: ( ( ( v1 COMMA )=> v2 | v1 ) )
            // MissileParser.g:85:5: ( ( v1 COMMA )=> v2 | v1 )
            {
            root_0 = (Object)adaptor.nil();

            // MissileParser.g:85:5: ( ( v1 COMMA )=> v2 | v1 )
            int alt7=2;
            switch ( input.LA(1) ) {
            case START_COMPOUND:
                {
                int LA7_1 = input.LA(2);

                if ( (synpred1_MissileParser()) ) {
                    alt7=1;
                }
                else if ( (true) ) {
                    alt7=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }
                }
                break;
            case COLON:
                {
                int LA7_2 = input.LA(2);

                if ( (synpred1_MissileParser()) ) {
                    alt7=1;
                }
                else if ( (true) ) {
                    alt7=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 2, input);

                    throw nvae;
                }
                }
                break;
            case NUMBER:
                {
                int LA7_3 = input.LA(2);

                if ( (synpred1_MissileParser()) ) {
                    alt7=1;
                }
                else if ( (true) ) {
                    alt7=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // MissileParser.g:85:6: ( v1 COMMA )=> v2
                    {
                    pushFollow(FOLLOW_v2_in_index_expr437);
                    v240=v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, v240.getTree());

                    }
                    break;
                case 2 :
                    // MissileParser.g:85:24: v1
                    {
                    pushFollow(FOLLOW_v1_in_index_expr440);
                    v141=v1();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, v141.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "index_expr"

    public static class begin_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "begin"
    // MissileParser.g:88:1: begin : index_expr ;
    public final MissileParser.begin_return begin() throws RecognitionException {
        MissileParser.begin_return retval = new MissileParser.begin_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        MissileParser.index_expr_return index_expr42 = null;



        try {
            // MissileParser.g:89:3: ( index_expr )
            // MissileParser.g:89:4: index_expr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_index_expr_in_begin456);
            index_expr42=index_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, index_expr42.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "begin"

    // $ANTLR start synpred1_MissileParser
    public final void synpred1_MissileParser_fragment() throws RecognitionException {   
        // MissileParser.g:85:6: ( v1 COMMA )
        // MissileParser.g:85:7: v1 COMMA
        {
        pushFollow(FOLLOW_v1_in_synpred1_MissileParser430);
        v1();

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred1_MissileParser432); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_MissileParser

    // Delegated rules

    public final boolean synpred1_MissileParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_MissileParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA2 dfa2 = new DFA2(this);
    static final String DFA2_eotS =
        "\13\uffff";
    static final String DFA2_eofS =
        "\2\uffff\1\3\2\uffff\1\7\5\uffff";
    static final String DFA2_minS =
        "\1\5\1\uffff\1\4\1\uffff\1\6\1\4\2\uffff\1\6\2\uffff";
    static final String DFA2_maxS =
        "\1\11\1\uffff\1\14\1\uffff\1\11\1\14\2\uffff\1\11\2\uffff";
    static final String DFA2_acceptS =
        "\1\uffff\1\1\1\uffff\1\2\2\uffff\1\6\1\3\1\uffff\1\4\1\5";
    static final String DFA2_specialS =
        "\13\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\1\3\uffff\1\2",
            "",
            "\1\3\1\4\6\uffff\1\3",
            "",
            "\1\6\2\uffff\1\5",
            "\1\7\1\10\6\uffff\1\7",
            "",
            "",
            "\1\12\2\uffff\1\11",
            "",
            ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "58:1: vector : ( ( all -> ^( TOK_ALL_COLON ) ) | single | range | ( single COLON single COLON single -> ^( COLON single single single ) ) | ( single COLON single COLON end_t -> ^( COLON single single end_t ) ) | ( single COLON end_t -> ^( COLON single end_t ) ) );";
        }
    }
 

    public static final BitSet FOLLOW_NUMBER_in_single63 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_all77 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_END_in_absolute_end94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_absolute_end_in_relative_end111 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NUMBER_in_relative_end113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_absolute_end_in_end_t130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relative_end_in_end_t138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_single_in_range155 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COLON_in_range157 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_single_in_range160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_all_in_vector183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_single_in_vector198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_range_in_vector206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_single_in_vector215 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COLON_in_vector217 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_single_in_vector219 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COLON_in_vector221 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_single_in_vector223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_single_in_vector246 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COLON_in_vector248 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_single_in_vector250 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COLON_in_vector252 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_end_t_in_vector254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_single_in_vector276 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COLON_in_vector278 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_end_t_in_vector280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_START_COMPOUND_in_compound309 = new BitSet(new long[]{0x0000000000000A20L});
    public static final BitSet FOLLOW_vector_in_compound313 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_compound_in_compound317 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_COMMA_in_compound323 = new BitSet(new long[]{0x0000000000000A20L});
    public static final BitSet FOLLOW_vector_in_compound327 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_compound_in_compound331 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_END_COMPOUND_in_compound336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_in_vector_expr355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vector_in_vector_expr361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vector_expr_in_v1379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vector_expr_in_v2397 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_COMMA_in_v2399 = new BitSet(new long[]{0x0000000000000A20L});
    public static final BitSet FOLLOW_vector_expr_in_v2401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_v2_in_index_expr437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_v1_in_index_expr440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_index_expr_in_begin456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_v1_in_synpred1_MissileParser430 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_COMMA_in_synpred1_MissileParser432 = new BitSet(new long[]{0x0000000000000002L});

}