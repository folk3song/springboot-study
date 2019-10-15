package com.springboot.chapter9.controller;


import com.itextpdf.text.BaseColor;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.springboot.chapter9.UserService.UserService;
import com.springboot.chapter9.pojo.User;
import com.springboot.chapter9.pojo.ValidatorPojo;
import com.springboot.chapter9.validator.UserValidator;
import com.springboot.chapter9.view.PdfExportService;
import com.springboot.chapter9.view.PdfView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.validation.Valid;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService = null;

    @RequestMapping("/table")
    public ModelAndView table()
    {
        List<User> userList = userService.findUsers(null,null);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/table");
        mv.addObject("userList",userList);
        return mv;
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id)
    {
        User user = userService.getUser(id);
        System.out.println(user);
        return user;
    }

    @RequestMapping("/list")
    public List<User> list(@RequestParam(value = "userName",required = false) String userName
    ,@RequestParam(value = "note",required = false) String note)
    {
        List<User> userList = userService.findUsers(userName,note);
        return userList;
    }

    @GetMapping("/converter")
    @ResponseBody
    public User getUserByConverter(User user)
    {
        return  user;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<User> list(List<User> userList)
    {
        return userList;
    }

    @GetMapping("/valid/page")
    public String validPage()
    {
        return "/validator/pojo";
    }

    @RequestMapping("/valid/validate")
    @ResponseBody
    public Map<String,Object> validate(
            @Valid @RequestBody ValidatorPojo vp, Errors errors
            ){
        Map<String,Object> errMap = new HashMap<>();
        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError oe:oes)
        {
            String key = null;
            String msg = null;
            if(oe instanceof FieldError)
            {
                FieldError fe = (FieldError) oe;
                key = fe.getField();
            }else {
                key = oe.getObjectName();
            }

            msg  = oe.getDefaultMessage();
            errMap.put(key,msg);

        }
        return errMap;

    }

    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        binder.setValidator(new UserValidator());
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),false));

    }

    @GetMapping("/validator")
    @ResponseBody
    public Map<String,Object> validator(@Valid User user,Errors errors,Date date)
    {
         Map<String,Object> map = new LinkedHashMap<>();
         map.put("user",user);
         map.put("date",date);

         if(errors.hasErrors())
         {
             List<ObjectError> oes = errors.getAllErrors();
             for(ObjectError os:oes)
             {
                 if(os instanceof FieldError)
                 {
                     FieldError fe = (FieldError) os;
                     map.put(fe.getField(),fe.getDefaultMessage());
                 }else {
                     map.put(os.getObjectName(),os.getDefaultMessage());
                 }
             }
         }
         return map;

    }

    @GetMapping("/export/pdf")
    public ModelAndView exportPdf(String userName,String note)
    {
        List<User> userList = userService.findUsers(userName,note);
        View view = new PdfView(exportService());
        ModelAndView mv = new ModelAndView();
        mv.setView(view);
        mv.addObject("userList",userList);
        return mv ;



    }

    @SuppressWarnings("unchecked")
    private PdfExportService exportService() {
        return (model, document, writer, request, response) -> {
            try {
                document.setPageSize(PageSize.A4);
                document.addTitle("用户信息");
                document.add(new Chunk("\n"));
                PdfPTable table = new PdfPTable(3);
                PdfPCell cell = null;
                Font f8 = new Font();
                f8.setColor(Color.BLUE);
                f8.setStyle(Font.BOLD);
                cell = new PdfPCell(new Paragraph("id", f8));
                cell.setHorizontalAlignment(1);

                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("user_name", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("note", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                List<User> userList = (List<User>) model.get("userList");
                for (User user : userList) {
                    document.add(new Chunk("\n"));
                    cell = new PdfPCell(new Paragraph(user.getId() + ""));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(user.getUserName() + ""));
                    table.addCell(cell);
                    String note = user.getNote() == null ? "" : user.getNote();
                    table.addCell(cell);
                }

                document.add(table);


            } catch (DocumentException e) {
                e.printStackTrace();
            }

        };

    }
}
