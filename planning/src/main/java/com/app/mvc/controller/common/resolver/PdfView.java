package com.app.mvc.controller.common.resolver;

import com.app.mvc.controller.common.Person;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * Created by Administrator on 2017/1/15.
 */
public class PdfView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, com.lowagie.text.pdf.PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

    }
}
