package com.avob.openadr.server.oadr20b.vtn;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.avob.openadr.server.common.vtn.models.demandresponseevent.DemandResponseEvent;
import com.avob.openadr.server.common.vtn.models.demandresponseevent.DemandResponseEventOadrProfileEnum;
import com.avob.openadr.server.common.vtn.models.demandresponseevent.DemandResponseEventSimpleValueEnum;
import com.avob.openadr.server.common.vtn.models.demandresponseevent.DemandResponseEventStateEnum;
import com.avob.openadr.server.common.vtn.models.ven.Ven;
import com.avob.openadr.server.common.vtn.models.ven.VenCreateDto;
import com.avob.openadr.server.common.vtn.models.vengroup.VenGroup;
import com.avob.openadr.server.common.vtn.models.vengroup.VenGroupDto;
import com.avob.openadr.server.common.vtn.models.venmarketcontext.VenMarketContext;
import com.avob.openadr.server.common.vtn.models.venmarketcontext.VenMarketContextDto;
import com.avob.openadr.server.common.vtn.service.DemandResponseEventService;
import com.avob.openadr.server.common.vtn.service.VenGroupService;
import com.avob.openadr.server.common.vtn.service.VenMarketContextService;
import com.avob.openadr.server.common.vtn.service.VenService;
import com.google.common.collect.Sets;

@Profile("test-functional")
@Configuration
public class EmbeddedSqlDatabaseInit {

	@Resource
	private VenMarketContextService venMarketContextService;

	@Resource
	private VenGroupService venGroupService;

	@Resource
	private VenService venService;

	@Resource
	private DemandResponseEventService demandeResponseEventService;

	@PostConstruct
	public void init() {
		String marketContextName = "http://oadr.avob.com";
		String marketContextDescription = "Avob Test Market Context";
		String marketcontextColor = "#90CAF9";
		VenMarketContext marketContext = venMarketContextService
				.prepare(new VenMarketContextDto(marketContextName, marketContextDescription, marketcontextColor));
		venMarketContextService.save(marketContext);

		marketContextName = "http://MarketContext1";
		marketContextDescription = "Test Market Context 1";
		marketcontextColor = "#ff8080";
		marketContext = venMarketContextService
				.prepare(new VenMarketContextDto(marketContextName, marketContextDescription, marketcontextColor));
		venMarketContextService.save(marketContext);

		String groupName = "OadrCACert";
		VenGroup oadrCaCert = venGroupService.prepare(new VenGroupDto(groupName));
		venGroupService.save(oadrCaCert);

		groupName = "CustomCACert";
		VenGroup customCaCert = venGroupService.prepare(new VenGroupDto(groupName));
		venGroupService.save(customCaCert);

		// rsa test ven
		VenCreateDto dto = new VenCreateDto();
		dto.setUsername("2E:55:12:81:B9:EE:9C:46:72:1D");
		dto.setAuthenticationType("x509");
		dto.setCommonName("test-rsa.oadr.com");
		dto.setOadrProfil("20b");
		Ven prepare = venService.prepare(dto);
		prepare.setVenMarketContexts(Sets.newHashSet(marketContext));
		prepare.setVenGroup(Sets.newHashSet(oadrCaCert));
		venService.save(prepare);

		// ecc test ven
		dto = new VenCreateDto();
		dto.setUsername("15:97:7B:DE:1C:1F:C6:D2:64:84");
		dto.setAuthenticationType("x509");
		dto.setCommonName("test-ecc.oadr.com");
		dto.setOadrProfil("20b");
		prepare = venService.prepare(dto);
		prepare.setVenMarketContexts(Sets.newHashSet(marketContext));
		prepare.setVenGroup(Sets.newHashSet(oadrCaCert));
		venService.save(prepare);

		// ven1.oadr.com
		dto = new VenCreateDto();
		dto.setUsername("05:8F:6B:7B:47:AF:EB:47:2A:7B");
		dto.setAuthenticationType("x509");
		dto.setCommonName("ven1.oadr.com");
		dto.setOadrProfil("20b");
		prepare = venService.prepare(dto);
		prepare.setVenMarketContexts(Sets.newHashSet(marketContext));
		prepare.setVenGroup(Sets.newHashSet(customCaCert));
		venService.save(prepare);

		// ven2.oadr.com
		dto = new VenCreateDto();
		dto.setUsername("5A:82:D3:9C:3C:98:41:BC:A5:4C");
		dto.setAuthenticationType("x509");
		dto.setCommonName("ven2.oadr.com");
		dto.setOadrProfil("20b");
		prepare = venService.prepare(dto);
		prepare.setVenMarketContexts(Sets.newHashSet(marketContext));
		prepare.setVenGroup(Sets.newHashSet(customCaCert));
		venService.save(prepare);
				
		DemandResponseEvent event = new DemandResponseEvent();
        String eventId = "eventId";
        Long createdTimestamp = System.currentTimeMillis();
        String duration = "PT1H";
        String notificationDuration = "P1D";
        Long lastUpdateTimestamp = 0L;
        int modification = 0;
        Long start = System.currentTimeMillis();
        Long startNotification = System.currentTimeMillis();
        DemandResponseEventStateEnum state = DemandResponseEventStateEnum.ACTIVE;
        DemandResponseEventSimpleValueEnum value = DemandResponseEventSimpleValueEnum.SIMPLE_SIGNAL_PAYLOAD_HIGH;

        event.setEventId(eventId);
        event.setValue(value);
        event.setState(state);
        event.setStart(start);
        event.setNotificationDuration(notificationDuration);
        event.setStartNotification(startNotification);
        event.setModificationNumber(modification);
        event.setMarketContext(marketContext);
        event.setLastUpdateTimestamp(lastUpdateTimestamp);
        event.setDuration(duration);
        event.setCreatedTimestamp(createdTimestamp);
        event.setOadrProfile(DemandResponseEventOadrProfileEnum.OADR20A);
        
        demandeResponseEventService.create(event);

	}
}