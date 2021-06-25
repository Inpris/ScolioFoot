package ru.inpris.bean.colors;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Stateless
public class ColorsList {
    @Resource(name = "jdbc/SCOLIO", mappedName = "jdbc/SCOLIO")
    private DataSource ds;

    public void loadSolids(List<SUpModel> list) {
        try (Connection connect = ds.getConnection();
             PreparedStatement stm = connect.prepareStatement("select solids_eng, instock from foot.sp_solids order by id")) {
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                list.add(new SUpModel(res.getString("solids_eng"), res.getBoolean("instock")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("mistake SolidsList load");
        }
    }

//    public void loadAllS(List<AllSolidsUpModel> list) {
//        try (Connection connect = ds.getConnection();
//             PreparedStatement stm = connect.prepareStatement("select id, solids from foot.sp_solids order by id")) {
//            ResultSet res = stm.executeQuery();
//            while (res.next()) {
//                list.add(new AllSolidsUpModel(res.getInt("id"), res.getString("solids")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("mistake solids load");
//        }
//    }
}
