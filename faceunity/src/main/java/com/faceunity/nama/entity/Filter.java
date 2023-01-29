package com.faceunity.nama.entity;

/**
 * 美颜滤镜
 *
 * @author Richie on 2020.02.23
 */
public class Filter {
    private String name;
    private int iconId;
    private int description;

    public Filter(String name, int iconId, int description) {
        this.name = name;
        this.iconId = iconId;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Filter filter = (Filter) o;
        return name != null ? name.equals(filter.name) : filter.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
