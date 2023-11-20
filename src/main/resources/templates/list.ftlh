[#ftl]
[@c.page]
    <div class="list">

        [#list businessCardsList as businessCard]
        <a href="/detail/${businessCard.getId()}">
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
        </a>
        [/#list]

        <a href="/new">
            <div class="businessCard businessCard--new">
                <svg width="100" height="100">
                    <rect width="20" height="100" x="40" y="0" fill="white"/>
                    <rect width="100" height="20" x="0" y="40" fill="white"/>
                </svg>
            </div>
        </a>
    </div>
[/@c.page]
