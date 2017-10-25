# InputFile
      InputFile方法中
       public abstract CharSequence filter (CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
       
       CharSequence source  为即将输入的字符串。source
       
       int start   source的start
       
        int endsource的end start为0，end也可理解为source长度了
       
       Spanned dest输入框中原来的内容，dest
       
       int dstart,    要替换或者添加的起始位置，即光标所在的位置
       
       int dend要替换或者添加的终止始位置，若为选择一串字符串进行更改，则为选中字符串 最后一个字符在dest中的位置。
       
       return CharSequence 

*过滤特殊字符
 
     InputFilter emojiFilter = new InputFilter() {
         //添加过滤规则
         Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        @Override
        public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
           //刷选字符按
            Matcher emojiMatcher = emoji.matcher(charSequence);
            //查看是否有满足过滤规则的字符
            if (emojiMatcher.find()) {
                Toast.makeText(MainActivity.this, "不支持输入表情", Toast.LENGTH_SHORT).show();
                return "";
            }
            return null;
        }
     };
     
*过滤二

    InputFilter inputFilter=new InputFilter() {
   
           //Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_]|[[:punct:]]");
           Pattern pattern = Pattern.compile("^[\\u4E00-\\u9FA5A-Za-z0-9]+$");
           @Override
           public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
               Matcher matcher= pattern.matcher(charSequence);
               if(matcher.find()){
                   Toast.makeText(MainActivity.this, "不支持输入表情", Toast.LENGTH_SHORT).show();
                   return "";
               }else{
                   return null;
               }
   
           }
       };
       
       