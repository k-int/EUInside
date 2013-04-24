package datamapping

import org.w3c.dom.Document
import org.xml.sax.InputSource

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

class TransformationService {

    def xls


    def transform(record , sourceType, targetType) {

    }


    public String transform(String xml, String xsl) throws javax.xml.transform.TransformerException {
    		String result = "";
    		System.setProperty("javax.xml.parsers.SAXParserFactory", "org.apache.xerces.jaxp.SAXParserFactoryImpl");
    		System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");

    	    StringWriter out = new StringWriter();

    	    TransformerFactory tFactory = TransformerFactory.newInstance();

    	    tFactory.setAttribute( FeatureKeys.DTD_VALIDATION, false );

    	    StreamSource xmlSource = new StreamSource(new StringReader(xml));
    	    StreamSource xslSource = new StreamSource(new StringReader(xsl));
    	    StreamResult xmlResult = new StreamResult(out);

    	    Transformer transformer = tFactory.newTransformer(xslSource);
    	    transformer.transform(xmlSource, xmlResult);
    	    result = out.toString();

    		return result;
    	}

    	/**
    	 * Alternative method of transformation. Needed for big files! Don't want to have them
    	 * in Strings.
    	 * @param xml
    	 * @param xsl
    	 * @param out
    	 * @throws TransformerException
    	 */
    	public void transformStream(InputStream xml, String xsl,OutputStream out) throws TransformerException {

    		TransformerFactory tFactory = TransformerFactory.newInstance();
    	    StreamSource xmlSource = new StreamSource(xml);
    	    StreamSource xslSource = new StreamSource(new StringReader(xsl));
    	    StreamResult xmlResult = new StreamResult(out);

    	    Transformer transformer = tFactory.newTransformer(xslSource);
    	    transformer.transform(xmlSource, xmlResult);
    	}

    	//using DOM, disabling validation

    	public void transform(InputStream xml, String xsl,OutputStream out ) throws Exception {
    		System.setProperty("javax.xml.parsers.SAXParserFactory", "org.apache.xerces.jaxp.SAXParserFactoryImpl");
    		System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");

    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            factory.setAttribute("http://xml.org/sax/features/namespaces", true);
            factory.setAttribute("http://xml.org/sax/features/validation", false);
            factory.setAttribute("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            factory.setAttribute("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            factory.setNamespaceAware(true);
            factory.setIgnoringElementContentWhitespace(false);
            factory.setIgnoringComments(false);
            factory.setValidating(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(xml));

            Source source = new DOMSource(document);

    		TransformerFactory tFactory = TransformerFactory.newInstance();


    	    StreamSource xslSource = new StreamSource(new StringReader(xsl));
    	    StreamResult xmlResult = new StreamResult(out);


    	    Transformer transformer = tFactory.newTransformer(xslSource);
    	    transformer.transform(source, xmlResult);
    	}


    	@Override
    	public String transform(String input) {
    		try {
    			return transform(input, xsl);
    		} catch (TransformerException e) {
    			e.printStackTrace();
    		}

    		return null;
    	}

       def resolve(String href, String base) throws TransformerException {

           //TODO change Config to xls path
       		String path = Config.getXSLPath(href);
       		StreamSource source = null;
       		try {
       			source = new StreamSource(new FileReader(path));
       		} catch (FileNotFoundException e) {
       			e.printStackTrace();
       		}

       		return source;


       }


}
