/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.common;

import java.io.InputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class FileDownloadController {

private StreamedContent file;

public FileDownloadController() {
InputStream stream = this.getClass().getResourceAsStream("primefaces.pdf");
file = new DefaultStreamedContent(stream, "application/pdf", "downloaded_primefaces.pdf");
}

public StreamedContent getFile() {
return file;
}

public void setFile(StreamedContent file) {
this.file = file;
}
}
