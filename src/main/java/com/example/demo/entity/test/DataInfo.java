package com.example.demo.entity.test;
/** * @author 作者 zuoruibo: 
    * @date 创建时间：2020年10月28日 下午2:47:00 
    * @version 1.0 
    * @parameter 
    * @since 
    * @return */
public class DataInfo {
	private Integer id ;
    private String cityName ;
    private String spell ;

    public DataInfo(Integer id, String cityName, String spell) {
        this.id = id;
        this.cityName = cityName;
        this.spell = spell;
    }

    @Override
    public String toString() {
        return "DataInfo{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", spell='" + spell + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }
}
