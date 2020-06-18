/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.AccountDAO;
import DTO.AccountDTO;
import java.util.ArrayList;

/**
 *
 * @author dangh
 */
public class AccountBUS {
    public  static  ArrayList<AccountDTO> Arr_account = new ArrayList();
    public  void docaccount() throws Exception
    {
        AccountDAO data = new AccountDAO();
        if(Arr_account == null)
            Arr_account = new ArrayList<>();
        Arr_account = data.docaccount();
        //ddd
    }
}
