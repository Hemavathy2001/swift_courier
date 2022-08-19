package com.example.swiftcourier.service;




import java.util.List;
import org.springframework.stereotype.Service;
import com.example.swiftcourier.dao.Coupon;
import com.example.swiftcourier.dto.CouponDTO;

/**
 * 
 * @author hemavathy.ravichandr
 * Includes Service methods adding and accessing coupon details from data base
 *
 */
@Service
public interface CouponService {
	
	
	
    /**
     * Method adds coupon to database 
     * @param coupon
     */
	public void addCoupon(Coupon coupon);
	
    /**
     * method returns all the coupons from database
     * @return
     */
	public List<CouponDTO> getAllCoupon() ;
    
	/**
	 * method returns a particular coupon from data based on couponId 
	 * @param couponId
	 * @return
	 */
	public CouponDTO getCoupon(int couponId);
	
	/**
	 * Method returns all unexpired coupon from database 
	 * @return
	 */
    public List<CouponDTO> getUnExpiredCoupon() ;

    /**
     * Method returns coupon based on eligibility for the user
     * @param couponId
     * @return
     */
    public boolean checkEligiblity(int couponId) ;
		
	
}
