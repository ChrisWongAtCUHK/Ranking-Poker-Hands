<div class="leaderboards-container"><div class="panel is-darkened"><h3 class="mtn">Description:</h3><div class="markdown" id="description"><p>A famous casino is suddenly faced with a sharp decline of their revenues. They decide to offer Texas hold'em also online. Can you help them by writing an algorithm that can rank poker hands? </p>
			<p><b style="font-size:16px">Task:</b></p>
			<ul>


				<li>Create a poker hand that has a method to compare itself to another poker hand:</li>

				<pre style="display: none;"><code class="lang-csharp">    Result PokerHand.CompareWith(PokerHand hand);</code></pre>
				<pre style="display: none;"><code class="lang-fsharp">    PokerHand.compareWith: hand: PokerHand -&gt; Result</code></pre>
				<pre><code class="lang-java">    Result PokerHand.compareWith(PokerHand hand);</code></pre>
				<pre style="display: none;"><code class="lang-javascript">    PokerHand.prototype.compareWith = <span class="hljs-function"><span class="hljs-keyword">function</span>(<span class="hljs-params">hand</span>)</span>{...};</code></pre>
				<li>A poker hand has a constructor that accepts a string containing 5 cards:</li>

				<pre style="display: none;"><code class="lang-csharp">    PokerHand hand = <span class="hljs-keyword">new</span> PokerHand(<span class="hljs-string">"KS 2H 5C JD TD"</span>);</code></pre>
				<pre style="display: none;"><code class="lang-fsharp">    <span class="hljs-keyword">let</span> hand = PokerHand(<span class="hljs-string">"KS 2H 5C JD TD"</span>)</code></pre>
				<pre><code class="lang-java">    PokerHand hand = <span class="hljs-keyword">new</span> PokerHand(<span class="hljs-string">"KS 2H 5C JD TD"</span>);</code></pre>
				<pre style="display: none;"><code class="lang-javascript">    <span class="hljs-keyword">var</span> hand = <span class="hljs-keyword">new</span> PokerHand(<span class="hljs-string">"KS 2H 5C JD TD"</span>);</code></pre>
				<li>The characteristics of the string of cards are:
				<ul>
					<li>A space is used as card seperator</li>
					<li>Each card consists of two characters</li>
					<li>The first character is the value of the card, valid characters are:
					<br><code>2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce)</code></li>
					<li>The second character represents the suit, valid characters are: 
					<br><code>S(pades), H(earts), D(iamonds), C(lubs)</code></li>
				</ul>
				</li>
				<br>
				<li>The result of your poker hand compare can be one of these 3 options:</li>

				<pre style="display: none;"><code class="lang-csharp">    <span class="hljs-keyword">public</span> <span class="hljs-keyword">enum</span> Result 
				{ 
					Win, 
					Loss, 
					Tie 
				}
				</code></pre>
				<pre style="display: none;"><code class="lang-fsharp">        <span class="hljs-class"><span class="hljs-keyword">type</span> <span class="hljs-title">Result</span> </span>=
							| Win = <span class="hljs-number">0</span> 
									| Loss = <span class="hljs-number">1</span>
											| Tie = <span class="hljs-number">2</span></code></pre>
				<pre><code class="lang-java">    <span class="hljs-keyword">public</span> <span class="hljs-keyword">enum</span> Result
	{
		WIN,
		LOSS,
		TIE
	}
				</code></pre>
											<pre style="display: none;"><code class="lang-javascript">    <span class="hljs-keyword">var</span> Result = 
													{
															<span class="hljs-string">"win"</span>: <span class="hljs-number">1</span>,
																	<span class="hljs-string">"loss"</span>: <span class="hljs-number">2</span>,
																			<span class="hljs-string">"tie"</span>: <span class="hljs-number">3</span>
																				}</code></pre>
											<li>Apply the <a href="https://en.wikipedia.org/wiki/Texas_hold_%27em" target="_blank">Texas Hold'em</a> rules for ranking the cards.</li>
											<br>
											<li>There is no ranking for the suits.</li>
										</ul>




										<p>
										<br></p>
							</div><div class="mtm"><span><i class="icon-moon-tag "></i></span><div class="keyword-tag">Algorithms</div><div class="keyword-tag">Object-oriented Programming</div><div class="keyword-tag">Games</div></div></div></div>
