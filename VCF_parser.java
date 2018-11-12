class VCF_parser{
static ArrayList<String> vcfToStringArray{
  FileInputStream s = new FileInputStream("path/to/vcf_file.vcf");//создаем поток из файла 
  BufferReader buf = new BufferReader(new InputStreamReader(s));//создаем буфер от входного потока
  String inputStr; //readedString или можно InputString или tmpString 
  ArrayList<String> propertiesFileStrokes = new ArrayList<String>();
  ArrayList<String> mutationFileStrokes = new ArrayList<String>();
  
  while ((rStr = buf.readLine())!= null) // 
  {
    if (rStr.charAt(0) == '#')
      propertiesFileStrokes.add(rStr);
    else 
    {
      mutationFileStrokes.add(rStr);
    }  
  }
}
  public static void main(String[] args){
    System.out.println("QQ")
    //В мейне будем писать только тесты для классов и методов, описанных выше.
    //не знаю, как тут компилить, можно копировать к себе и компилить там
    //так же на гит заливать буду это все сюда 
  }
}

