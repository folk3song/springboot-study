package com.springboot.chapter9.view;

import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.Document;
import java.util.Map;

public interface PdfExportService {
    public void make(Map<String,Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response);
}
