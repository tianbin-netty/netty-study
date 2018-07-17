package attribute;

import java.util.Date;

/**
 * @ProjectName: netty
 * @Title:
 * @Package attribute
 * @Description: (用一句话描述该文件做什么)
 * @User tianbin
 * @Date 2018/5/22 17:31
 * @Version v1.0
 **/
public class ChannelID {
    
    String name;
    Date date;
    public ChannelID(String name,Date date) {
        // TODO Auto-generated constructor stub
        this.date = date;
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }





    
}
