package com.quantity.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.quantity.entity.QuantityMeasurementEntity;
import com.quantity.util.ConnectionUtil;

public class QuantityMeasurementDBRepository implements IQuantityMeasurementRepository {

    @Override
    public void save(QuantityMeasurementEntity entity) {

        try {

            Connection conn = ConnectionUtil.getConnection();

            String sql="INSERT INTO quantity_measurement(operation,input1,input2,result) VALUES(?,?,?,?)";

            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setString(1,entity.getOperation());
            ps.setString(2,entity.getInput1());
            ps.setString(3,entity.getInput2());
            ps.setString(4,entity.getResult());

            ps.executeUpdate();

            System.out.println("Operation stored in DB");

        } catch(Exception e){
            e.printStackTrace();
        }
    }

	@Override
	public List<QuantityMeasurementEntity> findAll() {
		return null;
	}
}