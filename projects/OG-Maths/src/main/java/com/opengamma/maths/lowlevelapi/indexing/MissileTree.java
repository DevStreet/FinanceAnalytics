// $ANTLR 3.3 Nov 30, 2010 12:50:56 MissileTree.g 2013-07-02 13:08:05

/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

//CSOFF


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class MissileTree extends TreeParser {
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


        public MissileTree(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public MissileTree(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return MissileTree.tokenNames; }
    public String getGrammarFileName() { return "MissileTree.g"; }



    // $ANTLR start "walk"
    // MissileTree.g:24:1: walk returns [Index idx] : ( (a= test2D ) | (b= test1D ) );
    public final Index walk() throws RecognitionException {
        Index idx = null;

        TwoDIndex a = null;

        OneDIndex b = null;


        try {
            // MissileTree.g:25:6: ( (a= test2D ) | (b= test1D ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==TOK_TWODINDEX) ) {
                alt1=1;
            }
            else if ( ((LA1_0>=COMMA && LA1_0<=COLON)||LA1_0==NUMBER||LA1_0==TOK_ALL_COLON) ) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return idx;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // MissileTree.g:25:8: (a= test2D )
                    {
                    // MissileTree.g:25:8: (a= test2D )
                    // MissileTree.g:25:9: a= test2D
                    {
                    pushFollow(FOLLOW_test2D_in_walk61);
                    a=test2D();

                    state._fsp--;
                    if (state.failed) return idx;

                    }

                    if ( state.backtracking==0 ) {
                      idx =a;
                    }

                    }
                    break;
                case 2 :
                    // MissileTree.g:27:8: (b= test1D )
                    {
                    // MissileTree.g:27:8: (b= test1D )
                    // MissileTree.g:27:9: b= test1D
                    {
                    pushFollow(FOLLOW_test1D_in_walk81);
                    b=test1D();

                    state._fsp--;
                    if (state.failed) return idx;

                    }

                    if ( state.backtracking==0 ) {
                      idx =b;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return idx;
    }
    // $ANTLR end "walk"


    // $ANTLR start "test2D"
    // MissileTree.g:31:1: test2D returns [TwoDIndex idx] : ^( TOK_TWODINDEX a= vector b= vector ) ;
    public final TwoDIndex test2D() throws RecognitionException {
        TwoDIndex idx = null;

        List<IndexItem> a = null;

        List<IndexItem> b = null;


        try {
            // MissileTree.g:32:7: ( ^( TOK_TWODINDEX a= vector b= vector ) )
            // MissileTree.g:32:9: ^( TOK_TWODINDEX a= vector b= vector )
            {
            match(input,TOK_TWODINDEX,FOLLOW_TOK_TWODINDEX_in_test2D116); if (state.failed) return idx;

            match(input, Token.DOWN, null); if (state.failed) return idx;
            pushFollow(FOLLOW_vector_in_test2D120);
            a=vector();

            state._fsp--;
            if (state.failed) return idx;
            pushFollow(FOLLOW_vector_in_test2D124);
            b=vector();

            state._fsp--;
            if (state.failed) return idx;

            match(input, Token.UP, null); if (state.failed) return idx;
            if ( state.backtracking==0 ) {
              idx = new TwoDIndex(a, b);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return idx;
    }
    // $ANTLR end "test2D"


    // $ANTLR start "test1D"
    // MissileTree.g:36:1: test1D returns [OneDIndex idx] : (a= vector ) ;
    public final OneDIndex test1D() throws RecognitionException {
        OneDIndex idx = null;

        List<IndexItem> a = null;


        try {
            // MissileTree.g:37:6: ( (a= vector ) )
            // MissileTree.g:37:8: (a= vector )
            {
            // MissileTree.g:37:8: (a= vector )
            // MissileTree.g:37:9: a= vector
            {
            pushFollow(FOLLOW_vector_in_test1D160);
            a=vector();

            state._fsp--;
            if (state.failed) return idx;

            }

            if ( state.backtracking==0 ) {
              idx = new OneDIndex(a);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return idx;
    }
    // $ANTLR end "test1D"


    // $ANTLR start "vector"
    // MissileTree.g:41:1: vector returns [List<IndexItem> items = new ArrayList<IndexItem>(); ] : (a= expr ) ;
    public final List<IndexItem> vector() throws RecognitionException {
        List<IndexItem> items =  new ArrayList<IndexItem>();;

        List<IndexItem> a = null;


        try {
            // MissileTree.g:42:3: ( (a= expr ) )
            // MissileTree.g:42:4: (a= expr )
            {
            // MissileTree.g:42:4: (a= expr )
            // MissileTree.g:42:5: a= expr
            {
            pushFollow(FOLLOW_expr_in_vector190);
            a=expr();

            state._fsp--;
            if (state.failed) return items;

            }

            if ( state.backtracking==0 ) {
              items.addAll(a);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return items;
    }
    // $ANTLR end "vector"


    // $ANTLR start "expr"
    // MissileTree.g:46:1: expr returns [List<IndexItem> itemlist] options {backtrack=true; } : ^( ( COMMA | COLON | (val= tok_number ) | (val= all_colon ) ) (a= any_valid_subexpr | b= expr )* ) ;
    public final List<IndexItem> expr() throws RecognitionException {
        List<IndexItem> itemlist = null;

        IndexItem val = null;

        IndexItem a = null;

        List<IndexItem> b = null;


        try {
            // MissileTree.g:48:5: ( ^( ( COMMA | COLON | (val= tok_number ) | (val= all_colon ) ) (a= any_valid_subexpr | b= expr )* ) )
            // MissileTree.g:49:5: ^( ( COMMA | COLON | (val= tok_number ) | (val= all_colon ) ) (a= any_valid_subexpr | b= expr )* )
            {
            if ( state.backtracking==0 ) {
              itemlist  = new ArrayList<IndexItem>();
            }
            // MissileTree.g:51:10: ( COMMA | COLON | (val= tok_number ) | (val= all_colon ) )
            int alt2=4;
            switch ( input.LA(1) ) {
            case COMMA:
                {
                alt2=1;
                }
                break;
            case COLON:
                {
                alt2=2;
                }
                break;
            case NUMBER:
                {
                alt2=3;
                }
                break;
            case TOK_ALL_COLON:
                {
                alt2=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return itemlist;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // MissileTree.g:51:11: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_expr250); if (state.failed) return itemlist;

                    }
                    break;
                case 2 :
                    // MissileTree.g:52:11: COLON
                    {
                    match(input,COLON,FOLLOW_COLON_in_expr262); if (state.failed) return itemlist;

                    }
                    break;
                case 3 :
                    // MissileTree.g:53:11: (val= tok_number )
                    {
                    // MissileTree.g:53:11: (val= tok_number )
                    // MissileTree.g:53:12: val= tok_number
                    {
                    pushFollow(FOLLOW_tok_number_in_expr277);
                    val=tok_number();

                    state._fsp--;
                    if (state.failed) return itemlist;

                    }

                    if ( state.backtracking==0 ) {
                      itemlist.add(val);
                    }

                    }
                    break;
                case 4 :
                    // MissileTree.g:55:11: (val= all_colon )
                    {
                    // MissileTree.g:55:11: (val= all_colon )
                    // MissileTree.g:55:12: val= all_colon
                    {
                    pushFollow(FOLLOW_all_colon_in_expr304);
                    val=all_colon();

                    state._fsp--;
                    if (state.failed) return itemlist;

                    }

                    if ( state.backtracking==0 ) {
                      itemlist.add(val);
                    }

                    }
                    break;

            }


            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return itemlist;
                // MissileTree.g:58:10: (a= any_valid_subexpr | b= expr )*
                loop3:
                do {
                    int alt3=3;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==NUMBER) ) {
                        int LA3_2 = input.LA(2);

                        if ( (LA3_2==END||LA3_2==NUMBER) ) {
                            alt3=1;
                        }
                        else if ( (LA3_2==DOWN) ) {
                            alt3=2;
                        }


                    }
                    else if ( ((LA3_0>=COMMA && LA3_0<=COLON)||LA3_0==TOK_ALL_COLON) ) {
                        alt3=2;
                    }


                    switch (alt3) {
                	case 1 :
                	    // MissileTree.g:58:11: a= any_valid_subexpr
                	    {
                	    pushFollow(FOLLOW_any_valid_subexpr_in_expr341);
                	    a=any_valid_subexpr();

                	    state._fsp--;
                	    if (state.failed) return itemlist;
                	    if ( state.backtracking==0 ) {
                	      itemlist.add(a);
                	    }

                	    }
                	    break;
                	case 2 :
                	    // MissileTree.g:60:11: b= expr
                	    {
                	    pushFollow(FOLLOW_expr_in_expr366);
                	    b=expr();

                	    state._fsp--;
                	    if (state.failed) return itemlist;
                	    if ( state.backtracking==0 ) {
                	      itemlist.addAll(b);
                	    }

                	    }
                	    break;

                	default :
                	    break loop3;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return itemlist;
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return itemlist;
    }
    // $ANTLR end "expr"


    // $ANTLR start "any_valid_subexpr"
    // MissileTree.g:67:1: any_valid_subexpr returns [IndexItem item] options {backtrack=true; } : ( (a= computable_range ) | (a= stepped_computable_range ) | (a= offset_ended_stepped_range ) | (a= offset_ended_range ) | (a= ended_stepped_range ) | (a= ended_range ) );
    public final IndexItem any_valid_subexpr() throws RecognitionException {
        IndexItem item = null;

        IndexItem a = null;


        try {
            // MissileTree.g:69:3: ( (a= computable_range ) | (a= stepped_computable_range ) | (a= offset_ended_stepped_range ) | (a= offset_ended_range ) | (a= ended_stepped_range ) | (a= ended_range ) )
            int alt4=6;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // MissileTree.g:69:4: (a= computable_range )
                    {
                    // MissileTree.g:69:4: (a= computable_range )
                    // MissileTree.g:69:5: a= computable_range
                    {
                    pushFollow(FOLLOW_computable_range_in_any_valid_subexpr443);
                    a=computable_range();

                    state._fsp--;
                    if (state.failed) return item;

                    }

                    if ( state.backtracking==0 ) {
                      item = a;
                    }

                    }
                    break;
                case 2 :
                    // MissileTree.g:71:4: (a= stepped_computable_range )
                    {
                    // MissileTree.g:71:4: (a= stepped_computable_range )
                    // MissileTree.g:71:5: a= stepped_computable_range
                    {
                    pushFollow(FOLLOW_stepped_computable_range_in_any_valid_subexpr456);
                    a=stepped_computable_range();

                    state._fsp--;
                    if (state.failed) return item;

                    }

                    if ( state.backtracking==0 ) {
                      item = a;
                    }

                    }
                    break;
                case 3 :
                    // MissileTree.g:73:4: (a= offset_ended_stepped_range )
                    {
                    // MissileTree.g:73:4: (a= offset_ended_stepped_range )
                    // MissileTree.g:73:5: a= offset_ended_stepped_range
                    {
                    pushFollow(FOLLOW_offset_ended_stepped_range_in_any_valid_subexpr469);
                    a=offset_ended_stepped_range();

                    state._fsp--;
                    if (state.failed) return item;

                    }

                    if ( state.backtracking==0 ) {
                      item = a;
                    }

                    }
                    break;
                case 4 :
                    // MissileTree.g:75:4: (a= offset_ended_range )
                    {
                    // MissileTree.g:75:4: (a= offset_ended_range )
                    // MissileTree.g:75:5: a= offset_ended_range
                    {
                    pushFollow(FOLLOW_offset_ended_range_in_any_valid_subexpr482);
                    a=offset_ended_range();

                    state._fsp--;
                    if (state.failed) return item;

                    }

                    if ( state.backtracking==0 ) {
                      item = a;
                    }

                    }
                    break;
                case 5 :
                    // MissileTree.g:77:4: (a= ended_stepped_range )
                    {
                    // MissileTree.g:77:4: (a= ended_stepped_range )
                    // MissileTree.g:77:5: a= ended_stepped_range
                    {
                    pushFollow(FOLLOW_ended_stepped_range_in_any_valid_subexpr495);
                    a=ended_stepped_range();

                    state._fsp--;
                    if (state.failed) return item;

                    }

                    if ( state.backtracking==0 ) {
                      item = a;
                    }

                    }
                    break;
                case 6 :
                    // MissileTree.g:79:4: (a= ended_range )
                    {
                    // MissileTree.g:79:4: (a= ended_range )
                    // MissileTree.g:79:5: a= ended_range
                    {
                    pushFollow(FOLLOW_ended_range_in_any_valid_subexpr508);
                    a=ended_range();

                    state._fsp--;
                    if (state.failed) return item;

                    }

                    if ( state.backtracking==0 ) {
                      item = a;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return item;
    }
    // $ANTLR end "any_valid_subexpr"


    // $ANTLR start "computable_range"
    // MissileTree.g:84:1: computable_range returns [IndexItem item] : (a= NUMBER b= NUMBER ) ;
    public final IndexItem computable_range() throws RecognitionException {
        IndexItem item = null;

        CommonTree a=null;
        CommonTree b=null;

        try {
            // MissileTree.g:85:5: ( (a= NUMBER b= NUMBER ) )
            // MissileTree.g:85:6: (a= NUMBER b= NUMBER )
            {
            // MissileTree.g:85:6: (a= NUMBER b= NUMBER )
            // MissileTree.g:85:7: a= NUMBER b= NUMBER
            {
            a=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_computable_range545); if (state.failed) return item;
            b=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_computable_range549); if (state.failed) return item;

            }

            if ( state.backtracking==0 ) {
              item = new LinearIndex(Integer.valueOf((a!=null?a.getText():null)),Integer.valueOf((b!=null?b.getText():null)));
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return item;
    }
    // $ANTLR end "computable_range"


    // $ANTLR start "stepped_computable_range"
    // MissileTree.g:88:1: stepped_computable_range returns [IndexItem item] : (a= NUMBER b= NUMBER c= NUMBER ) ;
    public final IndexItem stepped_computable_range() throws RecognitionException {
        IndexItem item = null;

        CommonTree a=null;
        CommonTree b=null;
        CommonTree c=null;

        try {
            // MissileTree.g:89:5: ( (a= NUMBER b= NUMBER c= NUMBER ) )
            // MissileTree.g:89:6: (a= NUMBER b= NUMBER c= NUMBER )
            {
            // MissileTree.g:89:6: (a= NUMBER b= NUMBER c= NUMBER )
            // MissileTree.g:89:7: a= NUMBER b= NUMBER c= NUMBER
            {
            a=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_stepped_computable_range580); if (state.failed) return item;
            b=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_stepped_computable_range584); if (state.failed) return item;
            c=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_stepped_computable_range588); if (state.failed) return item;

            }

            if ( state.backtracking==0 ) {
              item = new LinearIndex(Integer.valueOf((a!=null?a.getText():null)),Integer.valueOf((b!=null?b.getText():null)),Integer.valueOf((c!=null?c.getText():null)));
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return item;
    }
    // $ANTLR end "stepped_computable_range"


    // $ANTLR start "ended_range"
    // MissileTree.g:93:1: ended_range returns [IndexItem item] : (a= NUMBER END ) ;
    public final IndexItem ended_range() throws RecognitionException {
        IndexItem item = null;

        CommonTree a=null;

        try {
            // MissileTree.g:94:5: ( (a= NUMBER END ) )
            // MissileTree.g:94:6: (a= NUMBER END )
            {
            // MissileTree.g:94:6: (a= NUMBER END )
            // MissileTree.g:94:7: a= NUMBER END
            {
            a=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_ended_range626); if (state.failed) return item;
            match(input,END,FOLLOW_END_in_ended_range628); if (state.failed) return item;

            }

            if ( state.backtracking==0 ) {
              item = new EndIndex(Integer.valueOf((a!=null?a.getText():null)));
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return item;
    }
    // $ANTLR end "ended_range"


    // $ANTLR start "ended_stepped_range"
    // MissileTree.g:98:1: ended_stepped_range returns [IndexItem item] : (a= NUMBER b= NUMBER END ) ;
    public final IndexItem ended_stepped_range() throws RecognitionException {
        IndexItem item = null;

        CommonTree a=null;
        CommonTree b=null;

        try {
            // MissileTree.g:99:5: ( (a= NUMBER b= NUMBER END ) )
            // MissileTree.g:99:6: (a= NUMBER b= NUMBER END )
            {
            // MissileTree.g:99:6: (a= NUMBER b= NUMBER END )
            // MissileTree.g:99:7: a= NUMBER b= NUMBER END
            {
            a=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_ended_stepped_range658); if (state.failed) return item;
            b=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_ended_stepped_range662); if (state.failed) return item;
            match(input,END,FOLLOW_END_in_ended_stepped_range664); if (state.failed) return item;

            }

            if ( state.backtracking==0 ) {
              item = new EndIndex(Integer.valueOf((a!=null?a.getText():null)),Integer.valueOf((b!=null?b.getText():null)));
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return item;
    }
    // $ANTLR end "ended_stepped_range"


    // $ANTLR start "offset_ended_range"
    // MissileTree.g:103:1: offset_ended_range returns [IndexItem item] : (a= NUMBER END b= NUMBER ) ;
    public final IndexItem offset_ended_range() throws RecognitionException {
        IndexItem item = null;

        CommonTree a=null;
        CommonTree b=null;

        try {
            // MissileTree.g:104:5: ( (a= NUMBER END b= NUMBER ) )
            // MissileTree.g:104:6: (a= NUMBER END b= NUMBER )
            {
            // MissileTree.g:104:6: (a= NUMBER END b= NUMBER )
            // MissileTree.g:104:7: a= NUMBER END b= NUMBER
            {
            a=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_offset_ended_range694); if (state.failed) return item;
            match(input,END,FOLLOW_END_in_offset_ended_range696); if (state.failed) return item;
            b=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_offset_ended_range700); if (state.failed) return item;

            }

            if ( state.backtracking==0 ) {
              item = new EndIndex(Integer.valueOf((a!=null?a.getText():null)),1,Integer.valueOf((b!=null?b.getText():null)));
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return item;
    }
    // $ANTLR end "offset_ended_range"


    // $ANTLR start "offset_ended_stepped_range"
    // MissileTree.g:108:1: offset_ended_stepped_range returns [IndexItem item] : (a= NUMBER b= NUMBER END c= NUMBER ) ;
    public final IndexItem offset_ended_stepped_range() throws RecognitionException {
        IndexItem item = null;

        CommonTree a=null;
        CommonTree b=null;
        CommonTree c=null;

        try {
            // MissileTree.g:109:5: ( (a= NUMBER b= NUMBER END c= NUMBER ) )
            // MissileTree.g:109:6: (a= NUMBER b= NUMBER END c= NUMBER )
            {
            // MissileTree.g:109:6: (a= NUMBER b= NUMBER END c= NUMBER )
            // MissileTree.g:109:7: a= NUMBER b= NUMBER END c= NUMBER
            {
            a=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_offset_ended_stepped_range730); if (state.failed) return item;
            b=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_offset_ended_stepped_range734); if (state.failed) return item;
            match(input,END,FOLLOW_END_in_offset_ended_stepped_range736); if (state.failed) return item;
            c=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_offset_ended_stepped_range740); if (state.failed) return item;

            }

            if ( state.backtracking==0 ) {
              item = new EndIndex(Integer.valueOf((a!=null?a.getText():null)),Integer.valueOf((b!=null?b.getText():null)),Integer.valueOf((c!=null?c.getText():null)));
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return item;
    }
    // $ANTLR end "offset_ended_stepped_range"


    // $ANTLR start "tok_number"
    // MissileTree.g:113:1: tok_number returns [IndexItem item] : (a= NUMBER ) ;
    public final IndexItem tok_number() throws RecognitionException {
        IndexItem item = null;

        CommonTree a=null;

        try {
            // MissileTree.g:114:3: ( (a= NUMBER ) )
            // MissileTree.g:114:4: (a= NUMBER )
            {
            // MissileTree.g:114:4: (a= NUMBER )
            // MissileTree.g:114:5: a= NUMBER
            {
            a=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_tok_number768); if (state.failed) return item;

            }

            if ( state.backtracking==0 ) {
              item = new GeneralIndex(Integer.valueOf((a!=null?a.getText():null)));
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return item;
    }
    // $ANTLR end "tok_number"


    // $ANTLR start "all_colon"
    // MissileTree.g:118:1: all_colon returns [IndexItem item] : TOK_ALL_COLON ;
    public final IndexItem all_colon() throws RecognitionException {
        IndexItem item = null;

        try {
            // MissileTree.g:119:3: ( TOK_ALL_COLON )
            // MissileTree.g:119:4: TOK_ALL_COLON
            {
            match(input,TOK_ALL_COLON,FOLLOW_TOK_ALL_COLON_in_all_colon789); if (state.failed) return item;
            if ( state.backtracking==0 ) {
              item = ColonIndex.getInstance();
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return item;
    }
    // $ANTLR end "all_colon"

    // $ANTLR start synpred1_MissileTree
    public final void synpred1_MissileTree_fragment() throws RecognitionException {   
        IndexItem a = null;


        // MissileTree.g:69:4: ( (a= computable_range ) )
        // MissileTree.g:69:4: (a= computable_range )
        {
        // MissileTree.g:69:4: (a= computable_range )
        // MissileTree.g:69:5: a= computable_range
        {
        pushFollow(FOLLOW_computable_range_in_synpred1_MissileTree443);
        a=computable_range();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred1_MissileTree

    // $ANTLR start synpred2_MissileTree
    public final void synpred2_MissileTree_fragment() throws RecognitionException {   
        IndexItem a = null;


        // MissileTree.g:71:4: ( (a= stepped_computable_range ) )
        // MissileTree.g:71:4: (a= stepped_computable_range )
        {
        // MissileTree.g:71:4: (a= stepped_computable_range )
        // MissileTree.g:71:5: a= stepped_computable_range
        {
        pushFollow(FOLLOW_stepped_computable_range_in_synpred2_MissileTree456);
        a=stepped_computable_range();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred2_MissileTree

    // $ANTLR start synpred3_MissileTree
    public final void synpred3_MissileTree_fragment() throws RecognitionException {   
        IndexItem a = null;


        // MissileTree.g:73:4: ( (a= offset_ended_stepped_range ) )
        // MissileTree.g:73:4: (a= offset_ended_stepped_range )
        {
        // MissileTree.g:73:4: (a= offset_ended_stepped_range )
        // MissileTree.g:73:5: a= offset_ended_stepped_range
        {
        pushFollow(FOLLOW_offset_ended_stepped_range_in_synpred3_MissileTree469);
        a=offset_ended_stepped_range();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred3_MissileTree

    // $ANTLR start synpred4_MissileTree
    public final void synpred4_MissileTree_fragment() throws RecognitionException {   
        IndexItem a = null;


        // MissileTree.g:75:4: ( (a= offset_ended_range ) )
        // MissileTree.g:75:4: (a= offset_ended_range )
        {
        // MissileTree.g:75:4: (a= offset_ended_range )
        // MissileTree.g:75:5: a= offset_ended_range
        {
        pushFollow(FOLLOW_offset_ended_range_in_synpred4_MissileTree482);
        a=offset_ended_range();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred4_MissileTree

    // $ANTLR start synpred5_MissileTree
    public final void synpred5_MissileTree_fragment() throws RecognitionException {   
        IndexItem a = null;


        // MissileTree.g:77:4: ( (a= ended_stepped_range ) )
        // MissileTree.g:77:4: (a= ended_stepped_range )
        {
        // MissileTree.g:77:4: (a= ended_stepped_range )
        // MissileTree.g:77:5: a= ended_stepped_range
        {
        pushFollow(FOLLOW_ended_stepped_range_in_synpred5_MissileTree495);
        a=ended_stepped_range();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred5_MissileTree

    // Delegated rules

    public final boolean synpred1_MissileTree() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_MissileTree_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_MissileTree() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_MissileTree_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_MissileTree() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_MissileTree_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_MissileTree() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_MissileTree_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_MissileTree() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_MissileTree_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA4 dfa4 = new DFA4(this);
    static final String DFA4_eotS =
        "\27\uffff";
    static final String DFA4_eofS =
        "\27\uffff";
    static final String DFA4_minS =
        "\1\11\1\6\2\3\1\2\1\3\1\uffff\1\2\1\uffff\1\2\1\uffff\1\2\1\uffff"+
        "\1\2\1\uffff\2\0\1\2\1\uffff\4\0";
    static final String DFA4_maxS =
        "\2\11\4\16\1\uffff\1\16\1\uffff\1\16\1\uffff\1\16\1\uffff\1\16\1"+
        "\uffff\2\0\1\16\1\uffff\4\0";
    static final String DFA4_acceptS =
        "\6\uffff\1\1\1\uffff\1\6\1\uffff\1\2\1\uffff\1\5\1\uffff\1\4\3\uffff"+
        "\1\3\4\uffff";
    static final String DFA4_specialS =
        "\17\uffff\1\3\1\4\2\uffff\1\2\1\1\1\0\1\5}>";
    static final String[] DFA4_transitionS = {
            "\1\1",
            "\1\3\2\uffff\1\2",
            "\3\6\1\5\2\uffff\1\4\4\uffff\1\6",
            "\3\10\3\uffff\1\7\4\uffff\1\10",
            "\1\6\3\12\1\6\2\uffff\1\11\4\uffff\1\12",
            "\3\14\3\uffff\1\13\4\uffff\1\14",
            "",
            "\1\10\3\16\1\10\2\uffff\1\15\4\uffff\1\16",
            "",
            "\1\12\3\6\1\20\2\uffff\1\17\4\uffff\1\6",
            "",
            "\1\14\3\22\1\14\2\uffff\1\21\4\uffff\1\22",
            "",
            "\1\16\3\10\1\24\2\uffff\1\23\4\uffff\1\10",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\22\3\14\1\26\2\uffff\1\25\4\uffff\1\14",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff"
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
            return "67:1: any_valid_subexpr returns [IndexItem item] options {backtrack=true; } : ( (a= computable_range ) | (a= stepped_computable_range ) | (a= offset_ended_stepped_range ) | (a= offset_ended_range ) | (a= ended_stepped_range ) | (a= ended_range ) );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TreeNodeStream input = (TreeNodeStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA4_21 = input.LA(1);

                         
                        int index4_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred3_MissileTree()) ) {s = 18;}

                        else if ( (synpred5_MissileTree()) ) {s = 12;}

                         
                        input.seek(index4_21);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA4_20 = input.LA(1);

                         
                        int index4_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_MissileTree()) ) {s = 14;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index4_20);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA4_19 = input.LA(1);

                         
                        int index4_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_MissileTree()) ) {s = 14;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index4_19);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA4_15 = input.LA(1);

                         
                        int index4_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_MissileTree()) ) {s = 6;}

                        else if ( (synpred2_MissileTree()) ) {s = 10;}

                         
                        input.seek(index4_15);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA4_16 = input.LA(1);

                         
                        int index4_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_MissileTree()) ) {s = 6;}

                        else if ( (synpred2_MissileTree()) ) {s = 10;}

                         
                        input.seek(index4_16);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA4_22 = input.LA(1);

                         
                        int index4_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred3_MissileTree()) ) {s = 18;}

                        else if ( (synpred5_MissileTree()) ) {s = 12;}

                         
                        input.seek(index4_22);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 4, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_test2D_in_walk61 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test1D_in_walk81 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_TWODINDEX_in_test2D116 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_vector_in_test2D120 = new BitSet(new long[]{0x0000000000004230L});
    public static final BitSet FOLLOW_vector_in_test2D124 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_vector_in_test1D160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_vector190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_expr250 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_COLON_in_expr262 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tok_number_in_expr277 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_all_colon_in_expr304 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_any_valid_subexpr_in_expr341 = new BitSet(new long[]{0x0000000000004238L});
    public static final BitSet FOLLOW_expr_in_expr366 = new BitSet(new long[]{0x0000000000004238L});
    public static final BitSet FOLLOW_computable_range_in_any_valid_subexpr443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stepped_computable_range_in_any_valid_subexpr456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_offset_ended_stepped_range_in_any_valid_subexpr469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_offset_ended_range_in_any_valid_subexpr482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ended_stepped_range_in_any_valid_subexpr495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ended_range_in_any_valid_subexpr508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_computable_range545 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NUMBER_in_computable_range549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_stepped_computable_range580 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NUMBER_in_stepped_computable_range584 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NUMBER_in_stepped_computable_range588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_ended_range626 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_END_in_ended_range628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_ended_stepped_range658 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NUMBER_in_ended_stepped_range662 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_END_in_ended_stepped_range664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_offset_ended_range694 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_END_in_offset_ended_range696 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NUMBER_in_offset_ended_range700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_offset_ended_stepped_range730 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NUMBER_in_offset_ended_stepped_range734 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_END_in_offset_ended_stepped_range736 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NUMBER_in_offset_ended_stepped_range740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_tok_number768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_ALL_COLON_in_all_colon789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_computable_range_in_synpred1_MissileTree443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stepped_computable_range_in_synpred2_MissileTree456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_offset_ended_stepped_range_in_synpred3_MissileTree469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_offset_ended_range_in_synpred4_MissileTree482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ended_stepped_range_in_synpred5_MissileTree495 = new BitSet(new long[]{0x0000000000000002L});

}