package adisys.server.frontEnd.controller ;


import java.io.*;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import adisys.server.business.ApplicationService;



public class ADISysAS extends ApplicationService
{
	
	public ADISysAS() throws ParserConfigurationException, SAXException, IOException {
		super();
	}

        
	@Override
	protected String getKeyNodeName() {
		return "action";
	}

	@Override
	protected InputStream getResourcePath() {
		//
		// return

		InputStream is = this.getClass().getResourceAsStream(
				"/adisys/server/frontEnd/controller/ConsultAC.xml");
		return is;
	}
	
}//End ScheduleBD