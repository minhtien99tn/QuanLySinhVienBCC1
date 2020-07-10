package com.example.quanlysinhvienbcc;

public class SinhVien {
    public int maSV;
    public String tenSV;
    public String diaChi;
    public String ngaysinh;
    public String sdt;
    public String msv;
    public String fb;
    public int gioitinh;
    public String anh;

    public int getMaSV() {
        return maSV;
    }

    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public SinhVien(String tenSV, String diaChi, String ngaysinh, String sdt, String msv, String fb, int gioitinh, String anh) {
        this.tenSV = tenSV;
        this.diaChi = diaChi;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.msv = msv;
        this.fb = fb;
        this.gioitinh = gioitinh;
        this.anh = anh;
    }

    public SinhVien(int maSV, String tenSV, String diaChi, String ngaysinh, String sdt, String msv, String fb, int gioitinh, String anh) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.diaChi = diaChi;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.msv = msv;
        this.fb = fb;
        this.gioitinh = gioitinh;
        this.anh = anh;
    }
}
