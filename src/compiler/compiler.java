/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

/**
 *
 * @author USER
 */


   /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




import java.io.*; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io. FileWriter;
import java.io.BufferedWriter;
import java.util.LinkedList;
import java.util.Queue; 
import java.util.Stack;

      
public class compiler {
private static String []lex=new String[100];

 public static  int num=0;
public static int count=0;
public static int countge=0;
  public static  Stack<String> codege = new Stack<String>();
    public static  Stack<String> opge = new Stack<String>();
  static String[] datatype=new String[100]; 
  private static int numdata=0;
  static String[]Variables=new String[100];

  static String []dataTemporary=new String [100];
  static int countdata=0;
    static int countdataTemporary=0;
  
   private static boolean isdatatype(String a){
   
   String [] Datatype={"int","double","float","char"};
 
     for(int i=0;i<Datatype.length;i++){
          
           if(Datatype[i].equals(a)){
               
        return true ;
       } }
          return false;
   
   }
  private static boolean iskeyword(String a){
       String [] keyword={"auto","break","case","char","const","continue","default",
           "do","double","else","enum","extern","float","for","goto",
	"if","int","long","register","return","short","signed",
	"sizeof","static","struct","switch","typedef","union",
	"unsigned","void","volatile","while"};
       for(int i=0;i<keyword.length;i++){
           if(keyword[i].equals(a))
        return true ;
       } 
          return false;
        
  }
  private static boolean logical(String b){
  String[] logica={"!","==",">","<",">=","<=","||","&&","|","&"};
  for(int i=0;i<logica.length;i++){
  if(logica[i].equals(b)){
  return true;
  }
  
  
  }
      return false;
  
  
  
  }
    private static boolean op(String w){
    String []opr={"+","*","/","-","%","=","++","--","-=","--","+="};
    for(int i=0;i<opr.length;i++){
    if(opr[i].equals(w)){
    return true;
    }
    }
    return false;
    
    }
    private static boolean Punctuators(String s){
    String [] pun={"!", "%", "^" ,"&", "*", "(", ")", "-" ,"+" ,"=", "{","}", "|","~","[","]"  ,";","'",":","\"","<",">","?",",",".","/","#","\\"};
    for(int i=0;i<pun.length;i++){
    if(pun[i].equals(s)){
        return true;
    }
   
    }
     return false;
       
    
    }
    private static boolean chek(String a ) throws IOException  {
  
    
      
   
     if( iskeyword(a)==true){
         if(isdatatype(a)==true){
          datatype[count]="isdatatype";
         }
         else{
             datatype[count]="iskeyword";}
                
                  
                   lex[count]=a;
                          System.out.println("iskeyword"+"\t"+a+"\t"+a.length());
count++;

                        return true;
                 }
                 else if(logical(a)==true){
                        datatype[count]="lojical";
                    lex[count]=a;
                     System.out.println("iskeyword"+"\t"+a+"\t"+a.length());
                    count++;  
                      return true;
                     
                 }
                 else if(op(a)==true){
                     datatype[count]="operator";
                    lex[count]=a;
                      System.out.println("iskeyword"+"\t"+a+"\t"+a.length());
             count++;  
                 return true;
                 
                 }
                 else if(Punctuators(a)==true){
              datatype[count]="Punctuators";
                    lex[count]=a;
                     System.out.println("iskeyword"+"\t"+a+"\t"+a.length());
            count++;  
                      return true;
                 
                 }
      return false;
    
    
    
    }
    private static boolean adddata(String a){
  
     
       num++;
     
       if( datatype[num].equals("Definition")){
           Variables[numdata++]=lex[num];
        
           num++;
          
        while(lex[num].equals(",")){
             
        num++;
         if( datatype[num]=="Definition"){
              Variables[numdata++]=lex[num];
         
            num++;}
 else
             return false;
        
        }
       }
      
          if(lex[num].equals("=")){
         
         
       return true;
       }
   
       if(lex[num++].equals(";")){
  
 return true;}
   
        
    return false;
    
    }
    private static void deornum(String a){
  char  g=a.charAt(0);
     if (Character.isLetter(g)){
   System.out.println("Definition"+"\t"+a+"\t"+a.length());
       datatype[count]=("Definition");
                    lex[count]=a;
                      count++;  
     }
     else{
         System.out.println("number"+"\t"+a+"\t"+a.length());
     datatype[count]=("number");
                    lex[count]=a;
                      count++;  
    }
    }
   private static void last(String a) throws IOException{
       
               int p=0;
String la="";
for (int i=0;i< a.length();i++){
          String m="";
        m=m+a.charAt(i);
            

if(op(m)==true ){
    if(!la.equals("")){
   deornum(la);
    
      la="";
    
    }
     int y=i;
   y++;
  String t="";
  t=t+a.charAt(y);
  if(op(t)==true ){
  m=m+t;
  i++;
  }
  if(chek(m)==true){}
   }
else if(Punctuators(m)==true){
   
 if(!la.equals("")){
       deornum(la);
      la="";
    
    }
  chek(m);

}

 else if(chek(m)==false ){
   if(a.length()-1==0){
  deornum(m);
   }
    if(la.equals("")){
 la=la+m;
 
  p=i+1;
  }
    else if(i-p==0){
     la=la+m;
     p++;
       if(chek(la)==true){
   la="";  
  
     }
       else if(i==a.length()-1){
   deornum(la);
      la="";
      la=la+m;
      p=i+1;
     }}
       else if(i-p!=0){
    
        deornum(la);
      la="";
      la=la+m;
      p=i+1;
     }
       else{
           int o=i+1;
           String h="";
           h=a.charAt(o)+h;
           if(op(h)==true){
              deornum(la);
      la="";
      la=la+m;
      p=i+1;
           }
           
       }
    }
   
 }}
 private static boolean  program(){
       
   if(!lex[num++].equals("void")){
      
       
      System.out.println("error in void");
      return false;
   
   }
   else if(!lex[num++].equals("main")){
       System.out.println("error in main");
          return false;
}
   else if(!lex[num++].equals("(")){
     System.out.println("error in left ( ");
       return false;
   }
   else if(!lex[num++].equals(")")){
    System.out.println("error in left )");
     return false;
   }
   else if(!lex[num++].equals("{")){
    System.out.println("error in left )");
    return false;
   }
    
  
  while(!"}".equals(lex[num])){
   
       if(lex[num].equals(null))
           return false;
     if(statements(lex[num])==false)
     return false;
 
  }
return  true;  
  }

   private static boolean chekdadt(String a){
for(int i=0;i<numdata;i++){
if(Variables[i].equals(a))
    
    return true;

}
return false;
 
 }
 private static String nonterminal(String a){
     
if(isdatatype(a)==true){
 
if(adddata(lex[num])==true){
  num--;
    
return "id";
}
        else
    return null;
} 
           
       
      char  g=a.charAt(0);
   
     if (Character.isLetter(g)){
             
      if(chekdadt(a)==true){

    return "id";
       }
      
     }
     else {
   return "number";
     }
   
           
           
               return null;
 
 
 
 }
  private static boolean simple ( String a){
     
if(nonterminal(a)=="id"){ 

return true;
    }
if(nonterminal(a)=="number"){
return true;

}
 else return false;
 
 }
 private static   boolean relational(String a){
    
 if(a.equals("<")||a.equals("<=")|| a.equals(">")|| a.equals(">=")|| a.equals("==")|| a.equals("!=")  ){
      
if(simple(lex[num++])==true){
    
    return true;
}}
 return false;
 }
  private static   boolean structured(String a){
    
  if(a.equals("&&")||a.equals("||"))
      return true;
  return false;
  
  }
private static boolean lexpPrime(String a){
  
    if(a.equals(")"))
        return true;
    else if(relational(a)==true ){
      
        return true;
    }
    else if(structured(a)==true ){
    return true;
     }
    

    return false;
}
  private static boolean Lexp(){
               
if(simple(lex[num++])==false){
   
    return false;
}

 if(lexpPrime(lex[num++])==false)
     return false;

    return true;
 
 }
   private static boolean ifprim(){
   if(lex[num++].equals("else")){
    if(lex[num++].equals("{"))
        if(statements(lex[num++])==false)
            return false;
    if(!lex[num++].equals("}")){
        return false;
    }
   else
         if(statements(lex[num++])==false);
            return false;
       }    
   else
   num--;
   return true;
   }
     private static boolean funif(){
        num=num+1;
         
      if(! lex[num++] .equals("(")){
             
    return false;
      }
  
if(Lexp()==true){
 
      if(! lex[num++].equals(")")){
          return false;}
     
      String a=lex[num];
      if(a.equals("{")){
          
      String b=lex[num++];
      if(!b.equals("}")){
      if(statements(b)==false)
          return false;
          if(!lex[num++].equals("}")){
          return false;
          }
      
      }
      return true;
      }
      else {
       if(statements(a)==false)
           
          return false;
      }
}
      if(ifprim()==true)
          return true;
    return true;
          
      }
      private static boolean factor(String a){
        
       
       if(nonterminal(a)=="id"){
          
                return true;
       }
                
               if(nonterminal(a)=="number")   {

          return true;}
       if(a.equals("(")){
           if(exp()==false)
           return false;
           
       }
          if(!lex[compiler.num++].equals(")"))
              return false;
          return true;
          
      }
      private static boolean termprim(String a){
          if(a.equals("/")||a.equals("*")||a.equals("%")){
              if(factor(lex[num++])==false)
                  return false;
            if(lex[num++].equals("/")||lex[num++].equals("*")||lex[num++].equals("%")){
                
               if( termprim(lex[num++])==false)
                return false;
                
            }}
           return true;
      }
      private static boolean expprim(String a){

       if(a.equals("-")||a.equals("+")){
           num++;
        
           if(term()==false){
                 
               return false;}
         if( lex[num].equals("-")||lex[num].equals("+")){
          if(expprim(lex[num])==false)
          return false;
         }
           
           
       } 
        
          return true;
          
      }
       private static boolean term(){
           
         if(factor(lex[num++])==false){
          
         return false;}
      
         if(termprim(lex[num])==false)
         return false;
 
         return true;
         
     }
       private static boolean  exp(){
                
           if(term()==false){
              
           return false;}
        String a=lex[num];
                    

           if(expprim(a)==false){
                
           return false;}
         
           if(lex[num].equals(")")){
             
           return true;
           
           
           }
    
           if(!lex[num++].equals(";")){
         
               return false;}
           
           return true;
           
       }
 private static boolean assignementstatement(){

    
     if(nonterminal(lex[num])!="id"){
       
         return false;}
     
   num++;

     if(!lex[num++].equals("="))
return false;
         
     if(exp()==false){
        
     return false;
     }
         
     return true;
             
             
     
 }
 private static boolean list(){
 if(assignementstatement()==false){
   
 return false;
 }
      

 if(lex[num].equals(","))
     if(list()==false){
         return false;
     }

        
 return true;

 }
  private static boolean funfor(){
      num=num+1;
  if(!lex[num++].equals("("))
      return false;
     
  if(list()==false){
       
return false;}
  num--;  
   
  if(!lex[num++].equals(";"))
  return false;
 
  if(Lexp()==false)
      return false;
 
   if(!lex[num++].equals(";"))
  return false;
 
    if(list()==false)
return false;  
       
    if(!lex[num++].equals(")"))
        return false;
    if(lex[num++].equals("{")){
    if(statements(lex[num++])==false)
        return false;
     if(!lex[num++].equals("}"))
         return false;}
    else{
       
        num--;
          
         if(statements(lex[num])==false)
        return false;
    }
    return true;
  }
  private static boolean whilefun(){
      num=num+1;
  if(!lex[num++].equals("("))
      return false;
  if(Lexp()==false)
      return false;
  if(!lex[num++].equals(")"))
      return false;
     if(lex[num++].equals("{")){
    if(statements(lex[num++])==false)
        return false;
     if(!lex[num++].equals("}"))
         return false;}
    else{
        num--;
          
         if(statements(lex[num])==false)
        return false;
    }
    return true;
  }
  private static boolean compoundstatement (){
  if(!nonterminal(lex[num++]).equals("id")){
  return false;}
  String a=lex[num++];
if(a.equals("++")||a.equals("--")&&lex[num++].equals(";")){
return true;
}

    return false;

  }
  private static boolean dofun(){
      num=num+1;
    String a=lex[num++];
if(a.equals("{")){
  if(compoundstatement ()==false)
    return false;
if(!lex[num++].equals("while")){
    return false;
}
    if(!lex[num++].equals("("))
        return false;
        if(Lexp()==false)
        return false;
        if(!lex[num++].equals(")"))
            return false;
         if(!lex[num++].equals("}"))
            return false;
}
else{
  if(compoundstatement ()==false)
    return false;
if(!lex[num++].equals("while")){
    return false;
}
    if(!lex[num++].equals("("))
        return false;
        if(Lexp()==false)
        return false;
        if(!lex[num++].equals(")"))
            return false;

}
return true;

}
  private static boolean morecases(){
      if(lex[num].equals("default "))
          return true;
  if(casesttement()==false)
      return false;
  return true;
  
  }
   private static boolean casesttement(){
     if(!lex[num++].equals("case"))
      return false;
       if(!nonterminal(lex[num++]).equals("number"))
      return false;
       if(!lex[num++].equals(":"))
      return false;
   if(statements(lex[num++])==false)
       return false;
 if(morecases()==false)
     return false;
   return true;
   }
  private static boolean switchfun(){
      num=num+1;
  if(!lex[num++].equals("("))
      return false;
  if(!nonterminal(lex[num++]).equals("id"))
      return false;
    if(!lex[num++].equals(")"))
      return false;
    if(!lex[num++].equals("{"))
      return false;
  if(casesttement()==false)
      return false;
  if(!lex[num++].equals("default "))
      return false;
  if(statements(lex[num++])==false)
      return false;
  if(!lex[num++].equals("}"))
      return false;
  return true;
  }
private static boolean statements(String lexca){

   
   if(isdatatype(lexca)==true){
     
 if(adddata(lexca)==false)
     
       return false;
   }
   else if(lexca.equals("if")){

if(funif()==false)
    return false;
}
else if(lexca.equals("for")){
if(funfor()==false)
    return false;
}
else if(lexca.equals("while")){
if(whilefun()==false)    
    return false;
   }
else if(lexca.equals("do")){
    if(dofun()==false)
        return false;
}
else if(lexca.equals("switch")){
if(switchfun()==false)
    return false;


}
else{
  
    if(datatype[num].equals("Definition")){
    if ( assignementstatement()==false)
return false;
}
}
 


   
return true;
}
   
    private static boolean  parser(){
   
   if(num<0){
   System.out.println("no code");
   }
   num=0;

    if(program()==false){
       return false;

    }
    
    
    return true;
}
       private static boolean codeGeneration(){
   countge=5;
   System.out.println(lex[countge]);
   while(!lex[countge].equals("}")){
       codeGenerationStatements();
       
       
       
   }
  
   return true;
   
   }
       
     private static boolean DefinitionGeneration(){
         System.out.println("rvalue"  +"   "+lex[countge++]);
         while(lex[countge++].equals(",")){
               System.out.println("rvalue"  +"   "+lex[countge++]);
         }
         if(lex[countge].equals("=")){
        opge.add(lex[countge++]);
            System.out.println("push"  +"   "+lex[countge++]);
                System.out.println(opge.pop());
         }
              
       return true;   
     }
      private static void printCodeGeneration() {
           if(datatype[countge]=="Definition")
         System.out.println("rvalue" +"  "+lex[countge++]);
         else
               System.out.println("push" +"  "+lex[countge++]);
      
      
      
      }
     private static boolean funifCodeGeneration(){
         countge=countge+2;
       
         if(datatype[countge]=="Definition")
         System.out.println("rvalue" +"  "+lex[countge++]);
         else
               System.out.println("push" +"  "+lex[countge++]);
         opge.add(lex[countge++]);
         if(datatype[countge]=="Definition")
         System.out.println("rvalue" +"  "+lex[countge++]);
         else
               System.out.println("number" +"  "+lex[countge++]);
              System.out.println(opge.pop());
         countge++;
          System.out.println("gofalse lb1");
         codeGenerationStatements();
             
         
           System.out.println("goto l2 ");
            System.out.println("lable lb1 ");
           System.out.println("lable lb2 ");
    return true;
         
         
         
         
     }
     private static boolean funforCodeGeneration(){
         countge=countge+2;
          
         if(isdatatype(lex[countge])==true){
      
         countge++;
    printCodeGeneration();
         }
         else{
          printCodeGeneration();
         
         }
           opge.add(lex[countge++]);
         printCodeGeneration();
          System.out.println(opge.pop());
             
            System.out.println("label lb1  ");
            countge++;
              printCodeGeneration();
              opge.add(lex[countge++]);
               printCodeGeneration();
               System.out.println(opge.pop());
            
            
            
             System.out.println("gofalse lb2");
               System.out.println("goto lb3");
                  System.out.println("label  lb4 ");
                  countge++;
                  System.out.println("lvalue"+"  "+lex[countge++]);
                  opge.add(lex[countge++]);
                    printCodeGeneration();
                    opge.add(lex[countge++]);
                      printCodeGeneration();
                       System.out.println(opge.pop());
                        System.out.println(opge.pop());
                        System.out.println("goto lb1");
                         System.out.println("label  lb3 ");
                          countge++;
                      codeGenerationStatements();
                       System.out.println("goto lb4");
                          System.out.println("label  lb2 ");
                      
                 
                  
         
         return true;
     }
     private static boolean funwhileCodeGeneration(){
         countge++;
         System.out.println("lable lb1");
         countge++;
         printCodeGeneration();
         opge.add(lex[countge++]);
           printCodeGeneration();
           System.out.println(opge.pop());
         System.out.println("goFalse lb2");
         countge++;
         codeGenerationStatements();
      
            System.out.println("goto lb1");
             System.out.println("lable lb2");
          
            
     return true;
     
     }
     private static boolean  funexpCodeGeneration(){
         System.out.println(  "lvalue"+"  "+lex[countge++]);
         opge.add(lex[countge++]);
          System.out.println("rvalue" +"  "+lex[countge++]);
          opge.add(lex[countge++]);
           System.out.println("rvalue" +"  "+lex[countge++]);
            System.out.println(opge.pop());
            System.out.println(opge.pop());
            countge++;
    return true;
     
     
     }
    private static boolean codeGenerationStatements(){
     if(isdatatype(lex[countge])==true){
      
         countge++;
         DefinitionGeneration();
     }
   else  if(lex[countge].equals("if")){
    
     
        funifCodeGeneration();
         
     }
     
   else if(lex[countge].equals("while")){
   funwhileCodeGeneration();
   
   }
   else  if(datatype[countge].equals("Definition")){
   funexpCodeGeneration();
   
   
   }
   else if(lex[countge].equals("for")){
   
   funforCodeGeneration();
   
   }
         return true;
         
         
    }  
    
    

    public static void main(String[] args) throws FileNotFoundException, IOException  {
        File a=new File("input.txt");
        Scanner s=new Scanner(a);
         String [] df = new String [100];
         int o=0;
       System.out.println("Type"+"\t\t"+"keyword"+"\t"+"length");
     while(s.hasNextLine()){
         String w=s.nextLine();
            String[] arr = w.split(" ");
            for(int i=0;i<arr.length;i++){
             if(chek(arr[i])==false){
                 
                 last(arr[i]);
                
             }
                }
           }


  if(parser()==true){
      System.out.println(" parser succeeded");
  codeGeneration();
  
  }
  else{
         
           System.out.println("parser failed");}
     System.gc();
 System.exit(0);  


}
}

