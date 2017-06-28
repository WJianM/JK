package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.domain.ExportProduct;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.domain.ExtEproduct;
import cn.itcast.jk.service.ExportService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

public class ExportServiceImpl implements ExportService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Export> find(String hql, Class<Export> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Export get(Class<Export> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Export> findPage(String hql, Page<Export> page, Class<Export> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	/**
	 * 保存或者更新用户
	 * 
	 * @throws MailInfoException
	 */
	public void saveOrUpdate(Export entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// 设置合同购销合同的状态 默认草稿
			entity.setState(0);
			// 查询所有该合同下所有货物
			String[] str = entity.getContractIds().split(", ");
			// 定义一个存储商品明细的set集合
			Set<ExportProduct> exportProductSets = new HashSet<>();
			// 设置合同号
			String customerContract = "";
			for (String cid : str) {
				// 将合同的状态设为2
				Contract contract = baseDao.get(Contract.class, cid);
				contract.setState(2);
				customerContract += contract.getContractNo() + " ";
				List<ContractProduct> contractProductList = baseDao.find("from ContractProduct where contract.id=?",
						ContractProduct.class, new Object[] { cid });
				// 遍历商品信息
				for (ContractProduct contractProduct : contractProductList) {
					ExportProduct exportProduct = new ExportProduct();
					exportProduct.setFactory(contractProduct.getFactory());
					exportProduct.setProductNo(contractProduct.getProductNo());
					exportProduct.setPackingUnit(contractProduct.getPackingUnit());
					exportProduct.setCnumber(contractProduct.getCnumber());
					exportProduct.setBoxNum(contractProduct.getBoxNum());
					// 查询所有的附件信息
					Set<ExtCproduct> extCproducts = contractProduct.getExtCproducts();
					// 定义一个保存附件的set集合
					Set<ExtEproduct> extEproductSets = new HashSet<>();
					for (ExtCproduct extCproduct : extCproducts) {
						ExtEproduct extEproduct = new ExtEproduct();
						BeanUtils.copyProperties(extCproduct, extEproduct);
						// 设置id
						extEproduct.setExportProduct(exportProduct);
						extEproduct.setId(null);
						extEproductSets.add(extEproduct);//
					}
					exportProduct.setExtEproducts(extEproductSets);
					exportProduct.setExport(entity);
					exportProductSets.add(exportProduct);
				}

				// 更新合同
				baseDao.saveOrUpdate(contract);

			}
			// 设置商品集合
			entity.setExportProducts(exportProductSets);
			// 设置合同号
			entity.setCustomerContract(customerContract);
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Export> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Export> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	public void delete(Class<Export> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}

	@Override
	public void update(Export returnExport) {
		// 更新
		Export export = baseDao.get(Export.class, returnExport.getId());
		export.setState(returnExport.getState());
		export.setRemark(returnExport.getRemark());
		baseDao.saveOrUpdate(export);
		Set<ExportProduct> set = returnExport.getExportProducts();
		for (ExportProduct exportProduct : set) {
			ExportProduct product = baseDao.get(ExportProduct.class, exportProduct.getId());
			product.setTax(exportProduct.getTax());
			baseDao.saveOrUpdate(product);
		}

	}
}
