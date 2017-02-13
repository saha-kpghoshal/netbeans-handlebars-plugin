/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package in.illimitable.handlebar.lexer.api;

import in.illimitable.javascript2.lexer.api.JsTokenId;
import in.illimitable.handlebar.lexer.HBSLexer;
import org.netbeans.api.html.lexer.HTMLTokenId;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import org.netbeans.api.lexer.InputAttributes;
import org.netbeans.api.lexer.Language;
import org.netbeans.api.lexer.LanguagePath;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenId;
import org.netbeans.spi.lexer.LanguageEmbedding;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author illimitable
 */
public enum HBSTokenId implements TokenId {

    HTML("html"),
    /** Contents inside <%# %> */
    JSCOMMENT("comment"),
    /** Contents inside <%= %> <%- %>*/
    JS_EXPR("js"),
    /** Contents inside <% %> */
    JS("js"),
    /** <% or %> */
    DELIMITER("hbs-delimiter"); 

    public static final String MIME_TYPE = "application/x-hbs"; // NOI18N
    
    private final String primaryCategory;
    
    public static boolean isJS(TokenId id) {
        return id == JS || id == JS_EXPR || id == JSCOMMENT;
    }

    HBSTokenId(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    @Override
    public String primaryCategory() {
        return primaryCategory;
    }

    // Token ids declaration
    private static final Language<HBSTokenId> language = new LanguageHierarchy<HBSTokenId>() {
        @Override
        protected Collection<HBSTokenId> createTokenIds() {
            return EnumSet.allOf(HBSTokenId.class);
        }
        
        @Override
        protected Map<String,Collection<HBSTokenId>> createTokenCategories() {
            return null;
        }
        
        @Override
        public Lexer<HBSTokenId> createLexer(LexerRestartInfo<HBSTokenId> info) {
            return new HBSLexer(info);
        }
        
        @Override
        protected LanguageEmbedding<? extends TokenId> embedding(Token<HBSTokenId> token,
                                  LanguagePath languagePath, InputAttributes inputAttributes) {
            switch(token.id()) {
                case HTML:
                    return LanguageEmbedding.create(HTMLTokenId.language(), 0, 0, true);
                case JS_EXPR:
                case JS:
                    return LanguageEmbedding.create(JsTokenId.javascriptLanguage(), 0, 0, false);
                default:
                    return null;
            }
        }
        
        @Override
        public String mimeType() {
            return HBSTokenId.MIME_TYPE;
        }
    }.language();
    
    public static Language<HBSTokenId> language() {
        return language;
    }
    
}
