[#ftl]
[@c.page]
    <div class="detail">
        <div class="businessCard">
            <div class="name">${businessCard.getName()}</div>
            <div class="company">${businessCard.getCompany()}</div>
            <div class="information">
                <div class="address">
                    <div>${businessCard.getStreet()}</div>
                    <div><span>${businessCard.getZipCode()}</span> <span>${businessCard.getTown()}</span></div>
                </div>
                <div class="contacts">
                    <div><i class="las la-at"></i> <span>${businessCard.getEmail()!informationNotProvided}</span></div>
                    <div><i class="las la-phone-alt"></i> <span>${businessCard.getPhoneNumber()!informationNotProvided}</span></div>
                    <div><i class="las la-globe"></i> <span>${businessCard.getWeb()!informationNotProvided}</span></div>
                </div>
            </div>
        </div>

        <div class="map">
            <iframe style="border:none" src="https://frame.mapy.cz/?q=${businessCard.getEntireAddress()?url}" width="100%" height="100%" frameborder="0"></iframe>
        </div>

        [@c.back /]
    </div>
[/@c.page]
