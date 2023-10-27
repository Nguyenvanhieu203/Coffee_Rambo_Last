/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.Data.Drink;
import Models.Data.Member;
import Models.MemberModel;
import java.util.List;

/**
 *
 * @author nguye
 */
public class MemberController {

    private final MemberModel _memberModel;
    public MemberController(MemberModel memberModel) {
        _memberModel = memberModel;
    }
    public List<Member> getAllmember() {
        List<Member> listResult = _memberModel.GetAllMember();
        if(listResult != null ) return listResult;
        return null;
    }
    public boolean PayWithouthScore(Member member, double totalPayment){
        boolean result = _memberModel.PayWithouthScore(member.getPhoneNumber(), totalPayment);
        return result;
    }
    public boolean PayWitScore(Member member, double totalPayment){
        boolean result = _memberModel.PayWitScore(member.getPhoneNumber(), totalPayment);
        return result;
    }
    public  List<Member> GetMemberByPhoneNumber (String phoneNumber){
        List<Member> resultList = _memberModel.GetMemberByPhoneNumber(phoneNumber);
        return resultList;
    }
    public boolean addMember(Member member) {
        boolean result = _memberModel.addMember(member);
        return result;
    }
}
