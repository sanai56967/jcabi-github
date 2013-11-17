/**
 * Copyright (c) 2012-2013, JCabi.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the jcabi.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jcabi.github;

import com.jcabi.aspects.Immutable;
import com.jcabi.aspects.Loggable;
import java.io.IOException;
import java.net.URL;
import javax.json.JsonObject;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Github commit.
 *
 * @author Yegor Bugayenko (yegor@tpc2.com)
 * @version $Id$
 * @since 0.3
 * @see <a href="http://developer.github.com/v3/pulls/">Pull Request API</a>
 * @see <a href="http://developer.github.com/v3/git/commits/">Commits API</a>
 */
@Immutable
public interface Commit extends Comparable<Commit> {

    /**
     * The repo we're in.
     * @return Repo
     */
    @NotNull(message = "repo is never NULL")
    Repo repo();

    /**
     * SHA of it.
     * @return SHA
     */
    @NotNull(message = "commit SHA is never NULL")
    String sha();

    /**
     * Describe it in a JSON object.
     * @return JSON object
     * @throws IOException If fails
     * @see <a href="http://developer.github.com/v3/git/commits/#get-a-commit">Get a Commit</a>
     */
    @NotNull(message = "JSON object is never NULL")
    JsonObject json() throws IOException;

    /**
     * Commit manipulation toolkit.
     */
    @Immutable
    @ToString
    @Loggable(Loggable.DEBUG)
    @EqualsAndHashCode(of = "commit")
    final class Tool {
        /**
         * Encapsulated commit.
         */
        private final transient Commit commit;
        /**
         * Public ctor.
         * @param cmt Commit
         */
        public Tool(final Commit cmt) {
            this.commit = cmt;
        }
        /**
         * Get its message.
         * @return Message of commit
         * @throws IOException If fails
         */
        public String message() throws IOException {
            return this.commit.json().getString("message");
        }
        /**
         * Get its URL.
         * @return URL of comment
         * @throws IOException If fails
         */
        public URL url() throws IOException {
            return new URL(this.commit.json().getString("url"));
        }
    }

}
