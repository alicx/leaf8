leaf8:androidDemo
===

android各种本地测试Demo，主要用来验证及实验新plu.

***

<h3>M:TouchDemo:</h3>
 * 目的：主要是验证view上的用户touch事件流的传递过程，从activity-》viewGroup-》view
        监听了touch的DispatchTouchEvent, onInterceptTouchEvent, onTouchEvent, onTouchListener之间的传递
 * 结果：touch事件从Activity的Dispatch开始传递给ViewGroup的Dispatch，在ViewGroup的Dispatch中会先调用onInterceptTouchEvent,
 <pre>
     A1： 如果该方法返回true，则表示ViewGroup将拦截该Touch事件，后续将直接调用ViewGroup的OnTouchListener/OnTouchEvent(*标1*onTouchListener总是会优先
 onTouchEvent方法调用，如果前者返回true则后者将不会被调用)。
 </pre>
 <pre>
    A2：如果该方法返回false，则将会继续传递给View的Dispatch，View作为子节点没有Intercept方法，将直接执行OnTouchListener/OnTouchEvent（如上标1）
 </pre>
 *特别提示*：如果一路下来都没有被拦截，则将会最后回到activity中的onTouchEvent方法中。
    [相关资料》》](http://www.cnblogs.com/xiaoyaoxia/p/4315489.html)
---