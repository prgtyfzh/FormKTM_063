/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS.A.FormKTM;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */

@Controller
public class ControllerProject {
    
    @RequestMapping("/mainpage")
    //@ResponseBody
    
    public String getData(@RequestParam("nama") String nama,
            @RequestParam("nim") String nim,
            @RequestParam("email") String email,
            @RequestParam("gambar") MultipartFile img,
            @RequestParam("ttl")@DateTimeFormat(pattern="yyyy-MM-dd") Date date,
            Model kiriman
            ) throws IOException {
        
        SimpleDateFormat newTanggal = new SimpleDateFormat("dd-MMMMM-yyyy");
        String tanggalKu = newTanggal.format(date);
        
        String blob = Base64.encodeBase64String(img.getBytes());      
        String isiimg = "data:image/*;base64, "+blob+" ";
        
        String logo = "Pictures/UMY.png;base64, "+blob+" ";
        
        kiriman.addAttribute("paketnama", nama);
        kiriman.addAttribute("paketnim", nim);
        kiriman.addAttribute("paketeml", email);
        kiriman.addAttribute("pakettgl", tanggalKu);
        kiriman.addAttribute("gambar", isiimg);
        kiriman.addAttribute("paketlogo", logo);
        
        return "viewpage";
    }
}
