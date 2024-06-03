package ru.cmstricks.tinyUrlWebApp.services;

import ru.cmstricks.tinyUrlWebApp.repositories.entities.FreeLink;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.PaidLink;

import java.util.Optional;

public interface LinksService {
    Optional<FreeLink> getFreeLinkById(String id);
    Optional<PaidLink> getPaidLinkById(String id);
    boolean isLinkPaid(String s);
    void createFreeLink(FreeLink freeLink);
    void createPaidLink(PaidLink paidLink);
    Integer createPreLinks();
}
