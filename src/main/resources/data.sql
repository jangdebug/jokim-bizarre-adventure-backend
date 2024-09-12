INSERT INTO `sivillage`.`brand`
(`brand_id`,
 `main_name`,
 `sub_name`,
 `badge_image`)
VALUES (1,
        "main1",
        "sub1",
        "www.image.com");

INSERT INTO `sivillage`.`product`
(`is_on_sale`,
 `price`,
 `brand_id`,
 `detail`,
 `product_name`)
VALUES (true,
        36000,
        1,
        "좋은 상품입니다",
        "쉐보레");



INSERT INTO `sivillage`.`color`
    (`value`)
VALUES ("실버");

INSERT INTO `sivillage`.`size`
    (`value`)
VALUES ("FREE");

INSERT INTO `sivillage`.`etc`
(`name`,
 `value`)
VALUES ("소재",
        "소가죽");


